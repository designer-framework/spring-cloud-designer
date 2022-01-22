package org.designer.function.provider;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/15 0:44
 */
public class Provider implements Function<String, String> {

    @Autowired
    private IService service;

    @Override
    public String apply(String pro) {
        service.print(pro);
        return "provider: " + getClass().getName() + pro;
    }

}
