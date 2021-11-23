package org.designer.rest.test;

import org.designer.rest.RestApplicationTest;
import org.designer.rest.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: Designer
 * @date : 2021/4/20 19:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplicationTest.class)
public class RestTest {

    @Autowired
    private TestService testService;

    @Test
    public void test() {
        String a = testService.get(123);
        String b = testService.post(123);
        String c = testService.get(123);
    }


}
