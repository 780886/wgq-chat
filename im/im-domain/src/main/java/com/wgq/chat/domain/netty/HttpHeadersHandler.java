package com.wgq.chat.domain.netty;

import com.sheep.utils.IpUtils;
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
            //获取请求路径
            request.setUri("/");
            //获取Netty内置的请求头对象
            HttpHeaders headers = request.headers();
            String token = headers.get("token");
            String ip = headers.get("X-Real-IP");
            NettyUtil.setAttr(ctx.channel(),NettyUtil.TOKEN,token);
            //如果没经过nginx，就直接获取远端地址
            if (StringUtils.isNullOrEmpty(ip)){
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                ip = address.getAddress().getHostAddress();
            }
            NettyUtil.setAttr(ctx.channel(),NettyUtil.IP, IpUtils.ipToLong(ip));
            ctx.pipeline().remove(this);
            ctx.fireChannelRead(request);
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    private static String getToken(String url){
//        String token = getToken(request.uri());;
        if (StringUtils.isNullOrEmpty(url)){
            return null;
        }
        if (!url.startsWith("/?token=")){
            return null;
        }
        return   url.replace("/?token=","");
    }
}
