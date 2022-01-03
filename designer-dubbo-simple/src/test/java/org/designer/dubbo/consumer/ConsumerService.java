package org.designer.dubbo.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.designer.dubbo.provider.ProvideService;
import org.springframework.stereotype.Component;

/**
 * @author designer [19901753334@163.com]
 * @since 2021/12/30 23:03
 */
@Component
public class ConsumerService {

    @DubboReference(version = "1.0.0", injvm = false, check = false)
    private ProvideService provideService;

    public void sayHelloMany(String name) {
        for (int i = 0; i < 100; i++) {
            provideService.sayHello(name);
        }
    }

}
