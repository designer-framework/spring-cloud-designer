package org.designer.mybatis.unit;

import org.designer.mybatis.SimpleTestApplication;
import org.designer.mybatis.mapper.XxlJobLogDynamicSqlSupport;
import org.designer.mybatis.model.XxlJobLog;
import org.designer.mybatis.service.XxlJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 22:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleTestApplication.class)
public class TestApplication {

    @Autowired
    private XxlJobService<XxlJobLog> xxlJobLogXxlJobService;


    @Test
    public void testCount() {
        final long count = xxlJobLogXxlJobService.count(
                select(count()).from(XxlJobLogDynamicSqlSupport.xxlJobLog)
                        .where(XxlJobLogDynamicSqlSupport.jobId, isEqualTo(1))
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
    }


    @Test
    public void testInsert() {
        xxlJobLogXxlJobService.insertBatch(Arrays.asList(new XxlJobLog()));
    }

}
