package com.wgq.chat.domain.service.strategy;

import com.sheep.exception.Asserts;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.constant.SheepError;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: MsgHandlerFactory
 * @Author : wgq
 * @Date :2023/9/14  17:06
 * @Description:
 * @Version :1.0
 */
public class MessageHandlerFactory {

    private static final Map<Integer, AbstractMessageHandler> STRATEGY_MAP = new HashMap<>();

    public static void register(Integer code, AbstractMessageHandler strategy) {
        STRATEGY_MAP.put(code, strategy);
    }

    public static AbstractMessageHandler getStrategyNoNull(Integer code) throws BusinessException {
        AbstractMessageHandler strategy = STRATEGY_MAP.get(code);
        Asserts.isTrue(Objects.isNull(strategy), SheepError.PARAM_VALID_FAILED);
        return strategy;
    }
}
