package org.designer.mq.configuration;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.designer.mq.consumer.RockConsumer;
import org.designer.mq.product.RockProduct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author designer [19901753334@163.com]
 * @since 2021/12/29 23:43
 */
@Configuration
public class RockMQConfig {

    @Bean
    RockConsumer rockConsumer() {
        return new RockConsumer();
    }

    @Bean
    RockProduct rockProduct(RocketMQTemplate rocketMQTemplate) {
        return new RockProduct(rocketMQTemplate);
    }

}
