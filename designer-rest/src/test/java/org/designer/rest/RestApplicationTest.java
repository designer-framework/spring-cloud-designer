package org.designer.rest;

import com.dtflys.forest.annotation.ForestClient;
import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: Designer
 * @date : 2021/4/20 19:06
 */
@ForestClient
@EnableFeignClients
@ForestScan(basePackages = "org.designer")
@SpringBootApplication(scanBasePackages = "org.designer")
public class RestApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(RestApplicationTest.class, args);
    }

}
