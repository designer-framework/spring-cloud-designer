package org.designer.mybatis.generator;

import org.designer.mybatis.base.service.IService;
import org.designer.mybatis.utils.MybatisUtils;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/8 0:24
 */
public class ServiceGenerator extends AbstractJavaGenerator {

    public static final String ATTR_SERVICE_TYPE = "ATTR_SERVICE_TYPE";

    private static final String ATTR_BASE_SERVICE_TYPE = "ATTR_BASE_SERVICE_TYPE";

    private static final String ATTR_BASE_SERVICE_VALUE = IService.class.getName();

    public ServiceGenerator(String project) {
        super(project);
    }

    /**
     * public interface XxlJobService extends IService<XxlJobLog> {
     *
     * @return
     */
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(Messages.getString("Progress.17", introspectedTable.getFullyQualifiedTable().toString()));
        //创建主类
        Interface serviceInterface = new Interface(getService());
        serviceInterface.setVisibility(JavaVisibility.PUBLIC);
        //继承基础Service并导入Service包
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(getShortBaseServiceClassName());
        serviceInterface.addImportedType(new FullyQualifiedJavaType(ATTR_BASE_SERVICE_VALUE));
        //指定继承类的泛型
        fullyQualifiedJavaType.addTypeArgument(new FullyQualifiedJavaType(getShortBaseRecordClassName()));
        //导入泛型类的包
        serviceInterface.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        //将生成的类集成到主类中
        serviceInterface.addSuperInterface(fullyQualifiedJavaType);

        //
        List<CompilationUnit> answer = new ArrayList<>();
        answer.add(serviceInterface);
        introspectedTable.setAttribute(ATTR_BASE_SERVICE_TYPE, ATTR_BASE_SERVICE_VALUE);
        introspectedTable.setAttribute(ATTR_SERVICE_TYPE, getService());
        return answer;
    }

    protected String getService() {
        return MybatisUtils.getFormatProperty(context, "javaServicePackage") + "." + getShortBaseRecordClassName() + "Service";
    }

    private String getShortBaseRecordClassName() {
        return MybatisUtils.shortName(introspectedTable.getBaseRecordType());
    }

    private String getShortBaseServiceClassName() {
        return MybatisUtils.shortName(ATTR_BASE_SERVICE_VALUE);
    }


}
