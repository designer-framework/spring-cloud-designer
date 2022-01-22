package org.designer.mq.product;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.designer.mq.constants.TestConstants;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author designer [19901753334@163.com]
 * @since 2021/12/29 23:54
 */
public class RockProduct {

    private final RocketMQTemplate rocketMQTemplate;

    public RockProduct(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public <T> void send(Class<T> clazz, T payload) {
        rocketMQTemplate.syncSend(TestConstants.TOPIC, MessageBuilder.withPayload(payload).build());
    }

}
