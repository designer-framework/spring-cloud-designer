package org.designer.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.designer.mybatis.base.BaseMapper;
import org.designer.mybatis.model.XxlJobLog;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.designer.mybatis.mapper.XxlJobLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface XxlJobLogMapper extends BaseMapper<XxlJobLog> {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, jobGroup, jobId, executorAddress, executorHandler, executorParam, executorShardingParam, executorFailRetryCount, triggerTime, triggerCode, handleTime, handleCode, alarmStatus, triggerMsg, handleMsg);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<XxlJobLog> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<XxlJobLog> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("XxlJobLogResult")
    Optional<XxlJobLog> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="XxlJobLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="job_group", property="jobGroup", jdbcType=JdbcType.INTEGER),
        @Result(column="job_id", property="jobId", jdbcType=JdbcType.INTEGER),
        @Result(column="executor_address", property="executorAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="executor_handler", property="executorHandler", jdbcType=JdbcType.VARCHAR),
        @Result(column="executor_param", property="executorParam", jdbcType=JdbcType.VARCHAR),
        @Result(column="executor_sharding_param", property="executorShardingParam", jdbcType=JdbcType.VARCHAR),
        @Result(column="executor_fail_retry_count", property="executorFailRetryCount", jdbcType=JdbcType.INTEGER),
        @Result(column="trigger_time", property="triggerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="trigger_code", property="triggerCode", jdbcType=JdbcType.INTEGER),
        @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="handle_code", property="handleCode", jdbcType=JdbcType.INTEGER),
        @Result(column="alarm_status", property="alarmStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="trigger_msg", property="triggerMsg", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="handle_msg", property="handleMsg", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<XxlJobLog> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, xxlJobLog, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, xxlJobLog, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(XxlJobLog record) {
        return MyBatis3Utils.insert(this::insert, record, xxlJobLog, c ->
            c.map(id).toProperty("id")
            .map(jobGroup).toProperty("jobGroup")
            .map(jobId).toProperty("jobId")
            .map(executorAddress).toProperty("executorAddress")
            .map(executorHandler).toProperty("executorHandler")
            .map(executorParam).toProperty("executorParam")
            .map(executorShardingParam).toProperty("executorShardingParam")
            .map(executorFailRetryCount).toProperty("executorFailRetryCount")
            .map(triggerTime).toProperty("triggerTime")
            .map(triggerCode).toProperty("triggerCode")
            .map(handleTime).toProperty("handleTime")
            .map(handleCode).toProperty("handleCode")
            .map(alarmStatus).toProperty("alarmStatus")
            .map(triggerMsg).toProperty("triggerMsg")
            .map(handleMsg).toProperty("handleMsg")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<XxlJobLog> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, xxlJobLog, c ->
            c.map(id).toProperty("id")
            .map(jobGroup).toProperty("jobGroup")
            .map(jobId).toProperty("jobId")
            .map(executorAddress).toProperty("executorAddress")
            .map(executorHandler).toProperty("executorHandler")
            .map(executorParam).toProperty("executorParam")
            .map(executorShardingParam).toProperty("executorShardingParam")
            .map(executorFailRetryCount).toProperty("executorFailRetryCount")
            .map(triggerTime).toProperty("triggerTime")
            .map(triggerCode).toProperty("triggerCode")
            .map(handleTime).toProperty("handleTime")
            .map(handleCode).toProperty("handleCode")
            .map(alarmStatus).toProperty("alarmStatus")
            .map(triggerMsg).toProperty("triggerMsg")
            .map(handleMsg).toProperty("handleMsg")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(XxlJobLog record) {
        return MyBatis3Utils.insert(this::insert, record, xxlJobLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(jobGroup).toPropertyWhenPresent("jobGroup", record::getJobGroup)
            .map(jobId).toPropertyWhenPresent("jobId", record::getJobId)
            .map(executorAddress).toPropertyWhenPresent("executorAddress", record::getExecutorAddress)
            .map(executorHandler).toPropertyWhenPresent("executorHandler", record::getExecutorHandler)
            .map(executorParam).toPropertyWhenPresent("executorParam", record::getExecutorParam)
            .map(executorShardingParam).toPropertyWhenPresent("executorShardingParam", record::getExecutorShardingParam)
            .map(executorFailRetryCount).toPropertyWhenPresent("executorFailRetryCount", record::getExecutorFailRetryCount)
            .map(triggerTime).toPropertyWhenPresent("triggerTime", record::getTriggerTime)
            .map(triggerCode).toPropertyWhenPresent("triggerCode", record::getTriggerCode)
            .map(handleTime).toPropertyWhenPresent("handleTime", record::getHandleTime)
            .map(handleCode).toPropertyWhenPresent("handleCode", record::getHandleCode)
            .map(alarmStatus).toPropertyWhenPresent("alarmStatus", record::getAlarmStatus)
            .map(triggerMsg).toPropertyWhenPresent("triggerMsg", record::getTriggerMsg)
            .map(handleMsg).toPropertyWhenPresent("handleMsg", record::getHandleMsg)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<XxlJobLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, xxlJobLog, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<XxlJobLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, xxlJobLog, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<XxlJobLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, xxlJobLog, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<XxlJobLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, xxlJobLog, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(XxlJobLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(jobGroup).equalTo(record::getJobGroup)
                .set(jobId).equalTo(record::getJobId)
                .set(executorAddress).equalTo(record::getExecutorAddress)
                .set(executorHandler).equalTo(record::getExecutorHandler)
                .set(executorParam).equalTo(record::getExecutorParam)
                .set(executorShardingParam).equalTo(record::getExecutorShardingParam)
                .set(executorFailRetryCount).equalTo(record::getExecutorFailRetryCount)
                .set(triggerTime).equalTo(record::getTriggerTime)
                .set(triggerCode).equalTo(record::getTriggerCode)
                .set(handleTime).equalTo(record::getHandleTime)
                .set(handleCode).equalTo(record::getHandleCode)
                .set(alarmStatus).equalTo(record::getAlarmStatus)
                .set(triggerMsg).equalTo(record::getTriggerMsg)
                .set(handleMsg).equalTo(record::getHandleMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(XxlJobLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(jobGroup).equalToWhenPresent(record::getJobGroup)
                .set(jobId).equalToWhenPresent(record::getJobId)
                .set(executorAddress).equalToWhenPresent(record::getExecutorAddress)
                .set(executorHandler).equalToWhenPresent(record::getExecutorHandler)
                .set(executorParam).equalToWhenPresent(record::getExecutorParam)
                .set(executorShardingParam).equalToWhenPresent(record::getExecutorShardingParam)
                .set(executorFailRetryCount).equalToWhenPresent(record::getExecutorFailRetryCount)
                .set(triggerTime).equalToWhenPresent(record::getTriggerTime)
                .set(triggerCode).equalToWhenPresent(record::getTriggerCode)
                .set(handleTime).equalToWhenPresent(record::getHandleTime)
                .set(handleCode).equalToWhenPresent(record::getHandleCode)
                .set(alarmStatus).equalToWhenPresent(record::getAlarmStatus)
                .set(triggerMsg).equalToWhenPresent(record::getTriggerMsg)
                .set(handleMsg).equalToWhenPresent(record::getHandleMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(XxlJobLog record) {
        return update(c ->
            c.set(jobGroup).equalTo(record::getJobGroup)
            .set(jobId).equalTo(record::getJobId)
            .set(executorAddress).equalTo(record::getExecutorAddress)
            .set(executorHandler).equalTo(record::getExecutorHandler)
            .set(executorParam).equalTo(record::getExecutorParam)
            .set(executorShardingParam).equalTo(record::getExecutorShardingParam)
            .set(executorFailRetryCount).equalTo(record::getExecutorFailRetryCount)
            .set(triggerTime).equalTo(record::getTriggerTime)
            .set(triggerCode).equalTo(record::getTriggerCode)
            .set(handleTime).equalTo(record::getHandleTime)
            .set(handleCode).equalTo(record::getHandleCode)
            .set(alarmStatus).equalTo(record::getAlarmStatus)
            .set(triggerMsg).equalTo(record::getTriggerMsg)
            .set(handleMsg).equalTo(record::getHandleMsg)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(XxlJobLog record) {
        return update(c ->
            c.set(jobGroup).equalToWhenPresent(record::getJobGroup)
            .set(jobId).equalToWhenPresent(record::getJobId)
            .set(executorAddress).equalToWhenPresent(record::getExecutorAddress)
            .set(executorHandler).equalToWhenPresent(record::getExecutorHandler)
            .set(executorParam).equalToWhenPresent(record::getExecutorParam)
            .set(executorShardingParam).equalToWhenPresent(record::getExecutorShardingParam)
            .set(executorFailRetryCount).equalToWhenPresent(record::getExecutorFailRetryCount)
            .set(triggerTime).equalToWhenPresent(record::getTriggerTime)
            .set(triggerCode).equalToWhenPresent(record::getTriggerCode)
            .set(handleTime).equalToWhenPresent(record::getHandleTime)
            .set(handleCode).equalToWhenPresent(record::getHandleCode)
            .set(alarmStatus).equalToWhenPresent(record::getAlarmStatus)
            .set(triggerMsg).equalToWhenPresent(record::getTriggerMsg)
            .set(handleMsg).equalToWhenPresent(record::getHandleMsg)
            .where(id, isEqualTo(record::getId))
        );
    }

}