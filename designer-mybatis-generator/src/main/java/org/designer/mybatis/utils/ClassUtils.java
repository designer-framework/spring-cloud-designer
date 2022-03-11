package org.designer.mybatis.utils;

import lombok.val;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/11 21:02
 */
public class ClassUtils {

    public static String shortName(String className) {
        return new FullyQualifiedJavaType(className).getShortName();
    }

    public static String shortRecordShortName(IntrospectedTable introspectedTable) {
        return shortRecordFullyQualifiedJavaType(introspectedTable).getShortName();
    }

    public static String getProperty(IntrospectedTable introspectedTable, String key) {
        return introspectedTable.getContext().getProperty(key);
    }

    public static FullyQualifiedJavaType shortRecordFullyQualifiedJavaType(IntrospectedTable introspectedTable) {
        return new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
    }

}
