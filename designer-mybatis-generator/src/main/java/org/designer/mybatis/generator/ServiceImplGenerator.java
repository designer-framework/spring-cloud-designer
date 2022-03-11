package org.designer.mybatis.generator;

import org.designer.mybatis.plugins.mapper.BaseMapperPlugin;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/8 0:24
 */
public class ServiceImplGenerator extends AbstractJavaGenerator {

    public static final String ATTR_SERVICE_IMPL_TYPE = "ATTR_SERVICE_IMPL_TYPE";

    private static final String ATTR_BASE_SERVICE_IMPL_VALUE = "org.designer.mybatis.base.service.impl.IServiceImpl";

    public ServiceImplGenerator(String project) {
        super(project);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(Messages.getString("Progress.17", introspectedTable.getFullyQualifiedTable().toString()));
        //
        TopLevelClass topLevelClass = new TopLevelClass(getServiceImpl());
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        //实现接口
        FullyQualifiedJavaType serviceInterface = new FullyQualifiedJavaType(getShortServiceName());
        topLevelClass.addSuperInterface(serviceInterface);
        topLevelClass.addImportedType(introspectedTable.getBaseRecordType());
        topLevelClass.addImportedType(getServiceName());
        //继承通用类
        FullyQualifiedJavaType baseServiceImplInterface = new FullyQualifiedJavaType(toShortClassName(ATTR_BASE_SERVICE_IMPL_VALUE));
        baseServiceImplInterface.addTypeArgument(new FullyQualifiedJavaType(getShortBaseRecordClassName()));
        baseServiceImplInterface.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType()));
        topLevelClass.setSuperClass(baseServiceImplInterface);
        topLevelClass.addImportedType(introspectedTable.getMyBatis3JavaMapperType());
        topLevelClass.addImportedType(ATTR_BASE_SERVICE_IMPL_VALUE);
        //导入工具类
        importStaticSqlUtils(topLevelClass);
        //
        List<CompilationUnit> answer = new ArrayList<>();
        answer.add(topLevelClass);
        introspectedTable.setAttribute(ATTR_SERVICE_IMPL_TYPE, ATTR_BASE_SERVICE_IMPL_VALUE);
        return answer;
    }

    String getServiceImpl() {
        return getContext().getProperty("javaServiceImplPackage") + "." + getShortServiceName() + "Impl";
    }

    String getShortServiceName() {
        return new FullyQualifiedJavaType(getServiceName()).getShortName();
    }

    String getServiceName() {
        return (String) introspectedTable.getAttribute(ServiceGenerator.ATTR_SERVICE_TYPE);
    }

    private void importStaticSqlUtils(TopLevelClass topLevelClass) {
        //代码中编写SQL语句时用到的  SQL语句构建工具类  及  字段工具类
        topLevelClass.addStaticImport(toStaticImport(SqlBuilder.class.getName()));
        topLevelClass.addStaticImport(toStaticImport(introspectedTable.getMyBatisDynamicSqlSupportType()));
    }

    private String toStaticImport(String className) {
        return toStaticImport(new FullyQualifiedJavaType(className));
    }

    private String toStaticImport(FullyQualifiedJavaType fullyQualifiedJavaType) {
        return fullyQualifiedJavaType.getFullyQualifiedNameWithoutTypeParameters() + ".*";
    }

    private String getShortBaseRecordClassName() {
        return toShortClassName(introspectedTable.getBaseRecordType());
    }

    private String toShortClassName(String className) {
        return new FullyQualifiedJavaType(className).getShortName();
    }

}
