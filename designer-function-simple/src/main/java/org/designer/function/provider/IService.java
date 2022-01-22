package org.designer.function.provider;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/15 16:28
 */
@Component
public class IService {

    public void print(String x) {
        System.out.println(x);
    }

}
