package org.designer.dubbo.configuration;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author designer [19901753334@163.com]
 * @since 2022/1/1 00:59
 */
@Configuration
public class DubboConfiguration {

    @Configuration
    @EnableDubbo(scanBasePackages = "org.designer.dubbo.consumer")
    @PropertySource("classpath:/spring/dubbo-consumer.properties")
    @ComponentScan(value = {"org.designer.dubbo.consumer"})
    static class ConsumerService {
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "org.designer.dubbo.provider.impl")
    @PropertySource("classpath:/spring/dubbo-provider.properties")
    @ComponentScan(value = {"org.designer.dubbo.provider.impl"})
    static class ProviderService {
    }


}
