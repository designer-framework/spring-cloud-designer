package org.designer.mybatis.unit;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.designer.mybatis.SimpleTestApplication;
import org.designer.student.mapper.XxlJobLogDynamicSqlSupport;
import org.designer.student.model.XxlJobLog;
import org.designer.student.service.XxlJobLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.mybatis.dynamic.sql.SqlBuilder.count;
import static org.mybatis.dynamic.sql.SqlBuilder.isNotNull;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 22:29
 */
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleTestApplication.class)
public class TestApplication {

    @Autowired
    private XxlJobLogService xxlJobLogService;

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        long count = xxlJobLogService.count(
                SqlBuilder.select(count()).from(XxlJobLogDynamicSqlSupport.xxlJobLog)
                        .where(XxlJobLogDynamicSqlSupport.jobId, isNotNull())
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
        log.warn("当前数据条数: {}", count);
        log.warn("结束测试Count");


        log.warn("单条数据插入测试-开始");
        long save = xxlJobLogService.save(xxlJobLog());
        long newCount = xxlJobLogService.count(
                SqlBuilder.select(count()).from(XxlJobLogDynamicSqlSupport.xxlJobLog)
                        .where(XxlJobLogDynamicSqlSupport.jobId, isNotNull())
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
        Assert.assertEquals("单条数据插入失败", (count + save), newCount);
        log.warn("单条数据插入测试-结束");


        log.warn("批量数据插入测试-开始");
        count = xxlJobLogService.count(
                SqlBuilder.select(count()).from(XxlJobLogDynamicSqlSupport.xxlJobLog)
                        .where(XxlJobLogDynamicSqlSupport.jobId, isNotNull())
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
        int insertBatchCount = xxlJobLogService.saveBatch(Arrays.asList(xxlJobLog(), xxlJobLog()));
        newCount = xxlJobLogService.count(
                SqlBuilder.select(count()).from(XxlJobLogDynamicSqlSupport.xxlJobLog)
                        .where(XxlJobLogDynamicSqlSupport.jobId, isNotNull())
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
        Assert.assertEquals("批量数据插入失败", (count + insertBatchCount), newCount);
        log.warn("批量数据插入测试-结束");
    }

    @SneakyThrows
    private XxlJobLog xxlJobLog() {
        XxlJobLog xxlJobLog = new XxlJobLog();
        xxlJobLog.setId(System.currentTimeMillis());
        Thread.sleep(0);
        xxlJobLog.setJobGroup(new Random().nextInt(1000000));
        xxlJobLog.setJobId(new Random().nextInt(1000000));
        xxlJobLog.setExecutorAddress("192.163.2.2");
        xxlJobLog.setExecutorHandler("h");
        xxlJobLog.setExecutorParam("p1");
        xxlJobLog.setExecutorShardingParam("sp1");
        xxlJobLog.setExecutorFailRetryCount(3);
        xxlJobLog.setTriggerTime(new Date());
        xxlJobLog.setTriggerCode(new Random().nextInt(1000000));
        xxlJobLog.setHandleTime(new Date());
        xxlJobLog.setHandleCode(new Random().nextInt(1000000));
        xxlJobLog.setAlarmStatus((byte) 1);
        xxlJobLog.setTriggerMsg("m1");
        xxlJobLog.setHandleMsg("m2");
        return xxlJobLog;
    }

}
