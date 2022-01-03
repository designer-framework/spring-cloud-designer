package org.designer.rest.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.designer.rest.converter.FormHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLDecoder;

/**
 * @author designer [19901753334@163.com]
 * @since 2021/12/12 17:11
 */
@Slf4j
@Configuration
public class FeignConfig {

    @Bean
    FormHttpMessageConverter extFormConverter() {
        return new FormHttpMessageConverter();
    }

    @Bean
    RequestInterceptor feignPrintLogInterceptor() {
        return new RequestInterceptor() {
            @SneakyThrows
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String body = new String(requestTemplate.request().body());
                log.info("url: {}, body: {}, query: {}", requestTemplate.url(), body, requestTemplate.queryLine());
                String utf8 = URLDecoder.decode(body, "utf8");
                log.info("Decoder: {}", body);
            }
        };
    }

}
