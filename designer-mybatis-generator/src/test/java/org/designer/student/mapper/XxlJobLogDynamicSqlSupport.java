package org.designer.student.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class XxlJobLogDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final XxlJobLog xxlJobLog = new XxlJobLog();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = xxlJobLog.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> jobGroup = xxlJobLog.jobGroup;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> jobId = xxlJobLog.jobId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> executorAddress = xxlJobLog.executorAddress;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> executorHandler = xxlJobLog.executorHandler;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> executorParam = xxlJobLog.executorParam;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> executorShardingParam = xxlJobLog.executorShardingParam;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> executorFailRetryCount = xxlJobLog.executorFailRetryCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> triggerTime = xxlJobLog.triggerTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> triggerCode = xxlJobLog.triggerCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> handleTime = xxlJobLog.handleTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> handleCode = xxlJobLog.handleCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> alarmStatus = xxlJobLog.alarmStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> uuid = xxlJobLog.uuid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> triggerMsg = xxlJobLog.triggerMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> handleMsg = xxlJobLog.handleMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class XxlJobLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> jobGroup = column("job_group", JDBCType.INTEGER);

        public final SqlColumn<Integer> jobId = column("job_id", JDBCType.INTEGER);

        public final SqlColumn<String> executorAddress = column("executor_address", JDBCType.VARCHAR);

        public final SqlColumn<String> executorHandler = column("executor_handler", JDBCType.VARCHAR);

        public final SqlColumn<String> executorParam = column("executor_param", JDBCType.VARCHAR);

        public final SqlColumn<String> executorShardingParam = column("executor_sharding_param", JDBCType.VARCHAR);

        public final SqlColumn<Integer> executorFailRetryCount = column("executor_fail_retry_count", JDBCType.INTEGER);

        public final SqlColumn<Date> triggerTime = column("trigger_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> triggerCode = column("trigger_code", JDBCType.INTEGER);

        public final SqlColumn<Date> handleTime = column("handle_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> handleCode = column("handle_code", JDBCType.INTEGER);

        public final SqlColumn<Byte> alarmStatus = column("alarm_status", JDBCType.TINYINT);

        public final SqlColumn<String> uuid = column("uuid", JDBCType.VARCHAR);

        public final SqlColumn<String> triggerMsg = column("trigger_msg", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> handleMsg = column("handle_msg", JDBCType.LONGVARCHAR);

        public XxlJobLog() {
            super("xxl_job_log");
        }
    }
}