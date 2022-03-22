package org.designer.mybatis.unit;

import lombok.extern.log4j.Log4j2;
import org.designer.mybatis.SimpleTestApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description:
 * @author: Designer
 * @date : 2021/11/23 22:29
 */
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleTestApplication.class)
public class TestApplication {
/*

    @Autowired
    private XxlJobLogService xxlJobLogService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testenhance() throws InstantiationException, IllegalAccessException {
        TestApplication testApplication = new TestApplication();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(testApplication.getClass());
        enhancer.setInterfaces(testApplication.getClass().getInterfaces());
        enhancer.setCallbackType(MethodInterceptor.class);
        Class classForProxy = enhancer.createClass();
        Object o = classForProxy.newInstance();
        o.toString();
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void test() throws JsonProcessingException {
        log.warn("T-测试Count-开始");
        long count = countData();
        log.warn("T-当前数据条数: {}", count);
        log.warn("T-测试Count-结束\n");


        log.warn("T-单条数据插入测试-开始");
        XxlJobLog saveCountObj = xxlJobLog();
        log.warn("T-待插入的一条数据: " + objectMapper.writeValueAsString(saveCountObj));
        long saveCount = xxlJobLogService.save(saveCountObj);
        Assert.assertEquals("T-单条数据插入失败", (count + saveCount), countData());
        log.warn("T-单条数据插入测试-结束\n");


        log.warn("T-查询一条数据-开始");
        log.warn("T-查询一条数据getById: " + xxlJobLogService.getById(saveCountObj.getId()));
        log.warn("T-查询一条数据getOne: " + xxlJobLogService.getOne(c -> c.where(id, isEqualTo(saveCountObj.getId()))));
        log.warn("T-查询一条数据list: " + xxlJobLogService.list(c -> c.where(id, isEqualTo(saveCountObj.getId()))));
        log.warn("T-查询一条数据-结束\n");


        log.warn("T-批量数据插入测试-开始");
        count = countData();
        int insertBatchCount = xxlJobLogService.saveBatch(Arrays.asList(xxlJobLog(), xxlJobLog()));
        Assert.assertEquals("T-批量数据插入失败", (count + insertBatchCount), countData());
        log.warn("T-批量数据插入测试-结束");


        log.warn("T-通过ID删除数据测试-开始");
        count = countData();
        int deleteCount1 = xxlJobLogService.deleteById(saveCountObj.getId());
        int deleteCount2 = xxlJobLogService.delete(c -> c.where(id, isEqualTo(saveCountObj.getId())));
        Assert.assertEquals("T-通过ID删除数据失败", 1, deleteCount1);
        Assert.assertEquals("T-通过ID删除数据失败", (count - deleteCount1), countData());
        log.warn("T-通过ID删除数据-结束");


        log.warn("T-插入不为空的字段-开始");
        count = countData();
        XxlJobLog saveSelective = xxlJobLog();
        saveSelective.setHandleMsg(null);
        saveSelective.setExecutorParam(null);
        int saveSelectiveCount = xxlJobLogService.saveSelective(saveSelective);
        Assert.assertEquals("T-插入不为空的字段失败", 1, saveSelectiveCount);
        Assert.assertEquals("T-插入不为空的字段失败", (count + saveSelectiveCount), countData());
        log.warn("T-插入的数据: {}", objectMapper.writeValueAsString(xxlJobLogService.getById(saveSelective.getId())));
        log.warn("T-插入不为空的字段-结束");


        log.warn("T-通过ID更新-开始");
        count = countData();
        saveSelective.setHandleMsg("updateById 更新后");
        int updateCount = xxlJobLogService.updateById(saveSelective);
        Assert.assertEquals("T-通过ID更新失败", 1, updateCount);
        //Assert.assertEquals("T-通过ID更新失败", (count + updateCount), countData());
        log.warn("T-更新的数据: {}", objectMapper.writeValueAsString(xxlJobLogService.getById(saveSelective.getId())));
        log.warn("T-通过ID更新-结束");


        log.warn("T-通过ID更新不为空的字段-开始");
        count = countData();
        saveSelective.setHandleMsg("updateByIdSelective 更新后");
        int updateSelectiveCount = xxlJobLogService.updateByIdSelective(reset(saveSelective));
        Assert.assertEquals("T-通过ID更新不为空的字段失败", 1, updateSelectiveCount);
        log.warn("T-更新后的数据: {}", objectMapper.writeValueAsString(xxlJobLogService.getById(saveSelective.getId())));
        log.warn("T-通过ID更新不为空的字段-结束");


        log.warn("T-通过Where更新不为空的字段-开始");
        count = countData();
        saveSelective.setHandleMsg("updateByIdSelective 更新后");
        int updateSelective = xxlJobLogService.updateSelective(reset(saveSelective), abstractWhereDSL -> {
            abstractWhereDSL.and(jobId, isEqualToWhenPresent(100));
        });
        int updateAll = xxlJobLogService.update(reset(saveSelective), abstractWhereDSL -> {
            abstractWhereDSL.and(jobId, isEqualToWhenPresent(100));
        });
        Assert.assertEquals("T-通过ID更新不为空的字段失败", 1, updateSelectiveCount);
        log.warn("T-更新后的数据: {}", objectMapper.writeValueAsString(xxlJobLogService.getById(saveSelective.getId())));
        log.warn("T-通过Where更新不为空的字段-结束");
    }

    public XxlJobLog reset(XxlJobLog xxlJobLog1) {
        XxlJobLog xxlJobLog = new XxlJobLog();
        xxlJobLog.setId(xxlJobLog1.getId());
        xxlJobLog.setHandleMsg(xxlJobLog1.getHandleMsg());
        return xxlJobLog;
    }

    public long countData() {
        return xxlJobLogService.count(c -> c.where(XxlJobLogDynamicSqlSupport.jobId, isNotNull()));
    }

    @SneakyThrows
    private XxlJobLog xxlJobLog() {
        XxlJobLog xxlJobLog = new XxlJobLog();
        xxlJobLog.setId(System.currentTimeMillis());
        Thread.sleep(1);
        log.warn("生成的数据ID: {}", xxlJobLog.getId());
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
*/

}
