package org.designer.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/3 16:41
 */
@Slf4j
@RocketMQMessageListener(topic = "default_topic", consumerGroup = "default_group")
public class RockConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("received message: {}", message);
    }

}