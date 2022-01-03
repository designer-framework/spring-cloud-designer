package org.designer.mq;

import org.designer.mq.product.RockProduct;
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
@SpringBootApplication(scanBasePackages = "org.designer.mq")
public class MQTestApplication implements ApplicationRunner {

    @Autowired
    private RockProduct rockProduct;

    public static void main(String[] args) {
        SpringApplication.run(MQTestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rockProduct.send(String.class, "");
    }

}
