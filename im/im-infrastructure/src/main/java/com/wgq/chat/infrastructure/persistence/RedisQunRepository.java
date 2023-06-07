package com.wgq.chat.infrastructure.persistence;


import com.sheep.support.PlaceHolderParser;
import com.sheep.support.PropertyAccessor;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.infrastructure.commons.PropertyAccessBuilder;
import com.wgq.chat.infrastructure.commons.RedisKey;
import com.wgq.chat.repository.QunRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RedisQunRepository implements QunRepository {

    @Inject
    private RedisTemplate redisTemplate;

    @Override public List<Integer> getUserIdList(String qunId) {
        PropertyAccessor propertyAccessor = PropertyAccessBuilder.buildByQunId(qunId);
        String userOfQunKey = PlaceHolderParser.parse(RedisKey.USER_ID_OF_QUN, propertyAccessor);
        List<String> originUserIds = this.redisTemplate.opsForList().range(userOfQunKey, 0, Integer.MAX_VALUE);
        if (CollectionsUtils.isNullOrEmpty(originUserIds)) {
            return Collections.emptyList();
        }
        List<Integer> userIds = new ArrayList<>(originUserIds.size());
        for (String userId : originUserIds) {
            userIds.add(Integer.parseInt(userId));
        }
        return userIds;
    }
}
