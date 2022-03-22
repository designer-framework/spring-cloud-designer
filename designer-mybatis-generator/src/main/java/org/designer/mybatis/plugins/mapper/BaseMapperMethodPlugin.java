package org.designer.mybatis.plugins.mapper;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;

import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/6 18:32
 */
public class BaseMapperMethodPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientUpdateAllColumnsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.setStatic(false);
        method.setDefault(true);
        return true;
    }

    @Override
    public boolean clientUpdateSelectiveColumnsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.setStatic(false);
        method.setDefault(true);
        return true;
    }



}
