package org.designer.rest.client;

import org.designer.rest.test.RestTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author designer [19901753334@163.com]
 * @since 2021/12/12 17:11
 */
@FeignClient(name = "service-a", url = "https://127.0.0.1", contextId = "FeignClient1")
public interface UrlencodedFeignClient {

    @RequestMapping(value = "/path", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void req(RestTest.Xxx xxx);

}
