package com.wgq.chat.domain.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName: NettyWebSocketServer
 * @Author : wgq
 * @Date :2023/8/22  14:06
 * @Description:
 * @Version :1.0
 */
@Configuration
public class NettyWebSocketServer {

    private Logger logger = LoggerFactory.getLogger(NettyWebSocketServer.class);

    public static final int WEB_SOCKET_PORT = 8090;
    // 创建线程池执行器
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup(NettyRuntime.availableProcessors());


    /**
     * websocket server
     *
     * @return
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        run();
    }

    public void run() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)//配置Channel的实现，这里使用NIO的TCP服务端Channel
                .option(ChannelOption.SO_BACKLOG, 128)//对于阻塞的连接队列大小的配置
                .option(ChannelOption.SO_KEEPALIVE, true)//开启keep-alive 会根据操作系统的设定保持长连接并检测连接可用性
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //30秒客户端没有向服务器发送心跳则关闭连接
                        pipeline.addLast(new IdleStateHandler(30, 0, 0));
                        // 因为使用http协议，所以需要使用http的编码器，解码器
                        pipeline.addLast(new HttpServerCodec());
                        // 以块方式写，添加 chunkedWriter 处理器
                        pipeline.addLast(new ChunkedWriteHandler());
                        /**
                         * 说明：
                         *  1. http数据在传输过程中是分段的，HttpObjectAggregator可以把多个段聚合起来；
                         *  2. 这就是为什么当浏览器发送大量数据时，就会发出多次 http请求的原因
                         */
                        //保存用户ip
                        pipeline.addLast(new HttpObjectAggregator(8192));
                        /**
                         * 说明：
                         *  1. 对于 WebSocket，它的数据是以帧frame 的形式传递的；
                         *  2. 可以看到 WebSocketFrame 下面有6个子类
                         *  3. 浏览器发送请求时： ws://localhost:7000/hello 表示请求的uri
                         *  4. WebSocketServerProtocolHandler 核心功能是把 http协议升级为 ws 协议，保持长连接；
                         *      是通过一个状态码 101 来切换的
                         */
                        pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                        //自定义handler ，处理业务逻辑
                        pipeline.addLast(new NettyWebSocketServerHandler());
                    }
                });
        //启动服务器，监听端口，阻塞直到启动成功
        serverBootstrap.bind(WEB_SOCKET_PORT).sync();
    }

    @PreDestroy
    public void destroy(){
        Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
        Future<?> workerGroupFuture = workerGroup.shutdownGracefully();
        bossGroupFuture.syncUninterruptibly();
        workerGroupFuture.syncUninterruptibly();
        logger.info("关闭 websocket server 成功");
    }

}
