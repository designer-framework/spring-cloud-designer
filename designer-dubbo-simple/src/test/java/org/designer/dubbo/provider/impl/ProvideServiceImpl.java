package org.designer.dubbo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.designer.dubbo.provider.ProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author designer [19901753334@163.com]
 * @since 2021/12/30 23:04
 */
@Slf4j
@DubboService(version = "1.0.0")
public class ProvideServiceImpl implements ProvideService {

    private static final String strTopic = "strTopic";
    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RocketMQProperties rocketMQProperties;

    @Override
    public void sayHello(String name) {
        rocketMQTemplate.asyncSend(
                rocketMQProperties.getConsumer().getTopic()
                , MessageBuilder.withPayload(String.format("[%s] : Hello, %s", serviceName, name)).build()
                , new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.warn(sendResult.toString());
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        throw new RuntimeException(throwable);
                    }
                });
    }

}
