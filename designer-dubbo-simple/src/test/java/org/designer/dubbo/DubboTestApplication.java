package org.designer.dubbo;

import org.designer.dubbo.consumer.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 22:29
 */
@SpringBootApplication(scanBasePackages = "org.designer")
public class DubboTestApplication implements ApplicationRunner {

    @Autowired
    private ConsumerService consumerService;

    public static void main(String[] args) {
        SpringApplication.run(DubboTestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        consumerService.sayHelloMany("application ");
    }

}
