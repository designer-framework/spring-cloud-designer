package org.designer.mybatis.unit;

import org.designer.mybatis.SimpleTestApplication;
import org.designer.mybatis.model.XxlJobLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 22:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleTestApplication.class)
public class TestApplication {

   /* @Autowired
    private XxlJobService xxlJobLogXxlJobService;


    @Test
    public void testCount() {
        final long count = xxlJobLogXxlJobService.count(
                select(count()).from(XxlJobLogDynamicSqlSupport.xxlJobLog)
                        .where(XxlJobLogDynamicSqlSupport.jobId, isEqualTo(1))
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
    }*/

/*
    @Test
    public void testInsert() {
        xxlJobLogXxlJobService.saveBatch(Arrays.asList(new XxlJobLog()));
    }*/

}
