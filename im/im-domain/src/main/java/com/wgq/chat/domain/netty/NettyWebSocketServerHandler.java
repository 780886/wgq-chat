package com.wgq.chat.domain.netty;

import com.sheep.core.spi.JsonFactory;
import com.sheep.json.Json;
import com.sheep.utils.SpringUtils;
import com.sheep.utils.StringUtils;
import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.protocol.dto.AuthorizeDTO;
import com.wgq.chat.protocol.dto.WebsocketBaseRequestDTO;
import com.wgq.chat.protocol.enums.ReqTypeEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: NettyWebSocketServerHandler
 * @Author : wgq
 * @Date :2023/8/22  14:40
 * @Description:
 * @Version :1.0
 */
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private Logger logger = LoggerFactory.getLogger(NettyWebSocketServerHandler.class);

    private WebSocketService webSocketService;

    private Json json = JsonFactory.getProvider();


    /**
     * Handler 被加入 Pipeline 时触发（仅仅触发一次）
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.webSocketService = getService();
    }

    /**
     * 客户端离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        userOffline(ctx);
    }

    /**
     * channel 连接就绪时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("触发 channelActive 上线![{}]", ctx.channel().id());
    }

    /**
     * channel 断开时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        logger.warn("触发 channelInactive 掉线![{}]", ctx.channel().id());
        userOffline(ctx);
    }

    /**
     * 心跳检查
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                // 关闭用户的连接
                userOffline(ctx);
            }
        }else if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete){
            this.webSocketService.connect(ctx.channel());
            String token = NettyUtil.getAttr(ctx.channel(), NettyUtil.TOKEN);
            if (!StringUtils.isNullOrEmpty(token)){
                this.webSocketService.authorize(ctx.channel(), new AuthorizeDTO(token));
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("异常发生 异常消息={}",cause);
        ctx.channel().close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        WebsocketBaseRequestDTO websocketBaseRequestDTO = json.parse(msg.text(), WebsocketBaseRequestDTO.class);
        ReqTypeEnum reqTypeEnum = ReqTypeEnum.of(websocketBaseRequestDTO.getType());
        switch (reqTypeEnum){
            case AUTHORIZE:
                this.webSocketService.authorize(channelHandlerContext.channel(),new AuthorizeDTO(websocketBaseRequestDTO.getToken()));
                break;
            case HEARTBEAT:
                break;
            case LOGIN:
                break;
        }
    }

    private WebSocketService getService() {
        return SpringUtils.getBean(WebSocketService.class);
    }

    /**
     * 用户离线
     * @param ctx
     */
    private void userOffline(ChannelHandlerContext ctx){
        this.webSocketService.removed(ctx.channel());
        ctx.channel().close();
    }
}
