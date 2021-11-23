package org.designer.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 19:06
 */
@SpringBootApplication(scanBasePackages = "org.designer")
public class SpringLockApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringLockApplicationTest.class, args);
    }

}
