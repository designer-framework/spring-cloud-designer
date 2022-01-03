package org.designer.dubbo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/3 17:19
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "default_group", topic = "default_topic")
public class RocketMQConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.warn("######## user_consumer received: {} ;\n", message);
    }

}
