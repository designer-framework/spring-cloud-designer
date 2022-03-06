package org.designer.mybatis.plugins.model;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/6 16:45
 */
public class SwaggerAnnotationPlugin extends PluginAdapter {

    private static final boolean USE_LOMBOK;

    static {
        boolean lombok;
        try {
            Class.forName("lombok.Data");
            lombok =  true;
        } catch (ClassNotFoundException e) {
            lombok =  false;
        }
        USE_LOMBOK = lombok;
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if(USE_LOMBOK){
            topLevelClass.addImportedType("lombok.Data");
            topLevelClass.addAnnotation("@Data");
            //
            topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
            topLevelClass.addAnnotation("@ApiModel(" + introspectedTable.getRemarks() + ")");
            //
            topLevelClass.addImportedType("lombok.EqualsAndHashCode");
            topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = true)");
        }
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        field.addAnnotation("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\")");
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
       return !USE_LOMBOK;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return !USE_LOMBOK;
    }

}
