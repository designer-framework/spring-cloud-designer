package org.designer.function.stream;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/16 16:10
 */
@EnableAutoConfiguration
@EnableBinding({Processor.class})
public class Transform {

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public String transform(String in) {
        return in + " world";
    }

    @StreamListener
    public void receive(@Input(Processor.OUTPUT) SubscribableChannel input, @Output(Processor.INPUT) MessageChannel output) {
    }

}
