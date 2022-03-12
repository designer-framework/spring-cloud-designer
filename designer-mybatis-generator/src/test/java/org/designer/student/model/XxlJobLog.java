package org.designer.student.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.designer.mybatis.base.entity.BaseEntity;

@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class XxlJobLog extends BaseEntity {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行器主键ID")
    private Integer jobGroup;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "任务，主键ID")
    private Integer jobId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行器地址，本次执行的地址")
    private String executorAddress;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行器任务handler")
    private String executorHandler;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行器任务参数")
    private String executorParam;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行器任务分片参数，格式如 1/2")
    private String executorShardingParam;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "失败重试次数")
    private Integer executorFailRetryCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "调度-时间")
    private Date triggerTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "调度-结果")
    private Integer triggerCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行-时间")
    private Date handleTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行-状态")
    private Integer handleCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败")
    private Byte alarmStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "调度-日志")
    private String triggerMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @ApiModelProperty(value = "执行-日志")
    private String handleMsg;
}