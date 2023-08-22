package com.wgq.chat.domain.netty;

import com.sheep.utils.StringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

import java.net.InetSocketAddress;

/**
 * @ClassName: HttpHeadersHandler
 * @Author : wgq
 * @Date :2023/8/22  14:24
 * @Description:
 * @Version :1.0
 */
public class HttpHeadersHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest){
            FullHttpRequest request = (FullHttpRequest) msg;
            //TODO
            String token = "";
            //获取请求路径
            request.setUri("1");
            //获取Netty内置的请求头对象
            HttpHeaders headers = request.headers();
            String ip = headers.get("X-Real-IP");
            //如果没经过nginx，就直接获取远端地址
            if (StringUtils.isNullOrEmpty(ip)){
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                ip = address.getAddress().getHostAddress();
            }
            NettyUtil.setAttr(ctx.channel(),NettyUtil.IP,ip);
            ctx.pipeline().remove(this);
            ctx.fireChannelRead(request);
        }else {
            ctx.fireChannelRead(msg);
        }
    }
}
