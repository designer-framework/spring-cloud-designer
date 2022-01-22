package org.designer.function.consumer;

import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/15 0:44
 */
@Component
public class Consumer implements Function<String, String> {

    @Override
    public String apply(String s) {
        return "request" + " + " + s;
    }

}
