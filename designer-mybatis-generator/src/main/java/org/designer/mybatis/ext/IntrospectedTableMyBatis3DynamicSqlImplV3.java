package org.designer.mybatis.ext;

import org.designer.mybatis.generator.DynamicSqlMapperGeneratorV3;
import org.designer.mybatis.generator.ServiceGenerator;
import org.designer.mybatis.generator.ServiceImplGenerator;
import org.designer.mybatis.utils.MybatisUtils;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.runtime.dynamic.sql.IntrospectedTableMyBatis3DynamicSqlImplV2;

import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/6 21:01
 */
public class IntrospectedTableMyBatis3DynamicSqlImplV3 extends IntrospectedTableMyBatis3DynamicSqlImplV2 {

    /**
     * 强制生成sql.xml
     *
     * @param javaClientGenerator
     * @param warnings
     * @param progressCallback
     */
    @Override
    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, List<String> warnings, ProgressCallback progressCallback) {
        if (javaClientGenerator == null) {
            if (context.getSqlMapGeneratorConfiguration() != null) {
                xmlMapperGenerator = new XMLMapperGenerator();
            }
        } else {
            xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
        }
        initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
    }

    @Override
    protected AbstractJavaClientGenerator calculateClientGenerators(List<String> warnings, ProgressCallback progressCallback) {
        return super.calculateClientGenerators(warnings, progressCallback);
    }

    @Override
    protected String calculateJavaClientInterfacePackage() {
        return getFormatProperty("javaClientPackage", super.calculateJavaClientInterfacePackage());
    }

    @Override
    protected String calculateJavaModelPackage() {
        return getFormatProperty("javaModelPackage", super.calculateJavaModelPackage());
    }

    @Override
    protected String calculateSqlMapPackage() {
        return getFormatProperty("sqlMapPackage", super.calculateSqlMapPackage());
    }

    @Override
    public String getMyBatis3XmlMapperPackage() {
        return getFormatProperty("xmlMapperPackage", super.getMyBatis3XmlMapperPackage());
    }

    @Override
    protected String getClientProject() {
        return getFormatProperty("clientProject", super.getClientProject());
    }


    @Override
    protected String getModelProject() {
        return getFormatProperty("moduleProject", super.getClientProject());
    }

    @Override
    public void calculateGenerators(List<String> warnings, ProgressCallback progressCallback) {
        super.calculateGenerators(warnings, progressCallback);
        calculateServiceGenerator(warnings, progressCallback);
        calculateServiceImplGenerator(warnings, progressCallback);
    }

    @Override
    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        return context.getJavaClientGeneratorConfiguration() == null ? null :
                new DynamicSqlMapperGeneratorV3(
                        getFormatProperty("javaMapperProject", super.getClientProject())
                );
    }

    /**
     * extends IServiceImpl<XxlJobLog, XxlJobLogMapper>
     *
     * @param warnings
     * @param progressCallback
     */
    protected void calculateServiceGenerator(List<String> warnings, ProgressCallback progressCallback) {
        AbstractJavaGenerator javaGenerator = new ServiceGenerator(getServiceProject());
        initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        javaGenerators.add(javaGenerator);
    }

    protected String getServiceProject() {
        return getFormatProperty("serviceProject", super.getClientProject());
    }

    /**
     * extends IServiceImpl<XxlJobLog, XxlJobLogMapper> implements XxlJobService<XxlJobLog>
     *
     * @param warnings
     * @param progressCallback
     */
    protected void calculateServiceImplGenerator(List<String> warnings, ProgressCallback progressCallback) {
        AbstractJavaGenerator javaGenerator = new ServiceImplGenerator(getServiceImplProject());
        initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        javaGenerators.add(javaGenerator);
    }

    protected String getServiceImplProject() {
        return getFormatProperty("serviceImplProject", super.getClientProject());
    }

    protected String getFormatProperty(String key, String... defaultVal) {
        return MybatisUtils.getFormatProperty(context, key, defaultVal);
    }

}
