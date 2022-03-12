package org.designer.student.service.impl;

import static org.designer.student.mapper.XxlJobLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import org.designer.mybatis.base.service.impl.IServiceImpl;
import org.designer.student.mapper.XxlJobLogMapper;
import org.designer.student.model.XxlJobLog;
import org.designer.student.service.XxlJobLogService;
import org.springframework.stereotype.Service;

@Service
public class XxlJobLogServiceImpl extends IServiceImpl<XxlJobLog, XxlJobLogMapper> implements XxlJobLogService {
}