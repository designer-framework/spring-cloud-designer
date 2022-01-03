package org.designer.rest.test;

import com.dtflys.forest.annotation.ForestClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.designer.rest.RestApplicationTest;
import org.designer.rest.client.UrlencodedFeignClient;
import org.designer.rest.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2021/4/20 19:06
 */
@Slf4j
@ForestClient()
//@EnableFeignClients(basePackages = "org.designer.rest")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableWebMvc
@SpringBootTest(classes = RestApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTest {

    @Autowired
    private TestService testService;

    @Autowired
    private UrlencodedFeignClient urlencodedFeignClient;

    @Test
    public void extForm() {
        C c = new C();
        c.setC("c");

        B b = new B();
        b.setB("b");
        b.setCList(Arrays.asList(c, c, c));

        Xxx xxx = new Xxx();
        xxx.setB(b);
        xxx.setA("a");
        xxx.setB(b);
        urlencodedFeignClient.req(xxx);
    }

    @Test
    public void http2() {
        String protocol = testService.http2(123);
        log.info(protocol);
    }

    @Data
    public static class Xxx {
        private String a;
        private B b;
    }

    @Data
    public static class B {
        private String b;
        private List<C> cList;
    }

    @Data
    public static class C {
        private String c;
    }


}
