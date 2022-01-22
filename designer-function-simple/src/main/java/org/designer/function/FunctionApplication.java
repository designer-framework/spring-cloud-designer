package org.designer.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 22:29
 */
@SpringBootApplication
public class FunctionApplication implements ApplicationRunner {

    @Autowired
    private Processor processor;

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SubscribableChannel input = processor.input();
        input.send(MessageBuilder.withPayload("zxc").build());
        MessageChannel output = processor.output();
    }

}
