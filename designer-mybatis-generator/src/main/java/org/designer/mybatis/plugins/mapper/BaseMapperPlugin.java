package org.designer.mybatis.plugins.mapper;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.springframework.util.ClassUtils;

import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/6 18:32
 */
public class BaseMapperPlugin extends PluginAdapter {

    private static final String BASE_MAPPER = "org.designer.mybatis.base.BaseMapper";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        interfaze.addImportedType(new FullyQualifiedJavaType(BASE_MAPPER));
        interfaze.addSuperInterface(new FullyQualifiedJavaType(getClientGenericInterfaceName(introspectedTable)));
        return true;
    }

    private String getClientGenericInterfaceName(IntrospectedTable introspectedTable) {
        return BASE_MAPPER + "<" + ClassUtils.getShortName(introspectedTable.getBaseRecordType()) +  ">";
    }

}
