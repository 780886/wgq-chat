package com.wgq.chat.domain.netty;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * Description: netty工具类
 * Author: wgq
 * Date: 2023-04-18
 */

public class NettyUtil {

    public static AttributeKey<String> TOKEN = AttributeKey.valueOf("token");
    public static AttributeKey<Long> IP = AttributeKey.valueOf("ip");
    public static AttributeKey<Long> USER_ID = AttributeKey.valueOf("userId");

    public static <T> void setAttr(Channel channel, AttributeKey<T> attributeKey, T data) {
        Attribute<T> attr = channel.attr(attributeKey);
        attr.set(data);
    }

    public static <T> T getAttr(Channel channel, AttributeKey<T> attributeKey) {
        return channel.attr(attributeKey).get();
    }
}
