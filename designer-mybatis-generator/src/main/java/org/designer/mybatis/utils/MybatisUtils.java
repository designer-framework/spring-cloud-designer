package org.designer.mybatis.utils;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;

import java.util.Properties;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/11 21:02
 */
public class MybatisUtils {

    public static String shortName(String className) {
        return new FullyQualifiedJavaType(className).getShortName();
    }

    public static FullyQualifiedJavaType shortFullyQualifiedJavaType(String className) {
        return new FullyQualifiedJavaType(shortName(className));
    }

    public static String getFormatProperty(Context context, String key, String... defaultVal) {
        if (defaultVal != null && defaultVal.length > 0) {
            return String.format(getProperties(context, key, defaultVal[0]), getProperties(context).getProperty("modelName"));
        } else {
            return String.format(getProperties(context, key, null), getProperties(context).getProperty("modelName"));
        }
    }

    protected static String getProperties(Context context, String key, String defaultVal) {
        return getProperties(context).getProperty(key, defaultVal);
    }


    protected static Properties getProperties(Context context) {
        return context.getProperties();
    }

}
