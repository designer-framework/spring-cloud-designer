package org.designer.mybatis.plugins.mapper;

import org.designer.mybatis.base.mapper.BaseMapper;
import org.designer.mybatis.utils.ClassUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;

import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/6 18:32
 */
public class BaseMapperPlugin extends PluginAdapter {

    public static final String BASE_MAPPER = BaseMapper.class.getName();

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        interfaze.addImportedType(new FullyQualifiedJavaType(BASE_MAPPER));
        interfaze.addSuperInterface(createMapperSuperInterfaceName(introspectedTable));
        return true;
    }

    /**
     * BaseMapper 加泛型
     *
     * @param introspectedTable
     * @return
     */
    private FullyQualifiedJavaType createMapperSuperInterfaceName(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(BASE_MAPPER);
        fullyQualifiedJavaType.addTypeArgument(ClassUtils.shortRecordFullyQualifiedJavaType(introspectedTable));
        return new FullyQualifiedJavaType(fullyQualifiedJavaType.getShortName());
    }

}
