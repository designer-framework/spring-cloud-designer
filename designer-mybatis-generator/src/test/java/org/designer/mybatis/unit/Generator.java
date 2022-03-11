package org.designer.mybatis.unit;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/6 20:05
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        //获取类加载器
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        //类加载器在classpach：下获取配置文件
        InputStream is = classloader.getResourceAsStream("mybatis-generator-config.xml");
        Configuration config = new ConfigurationParser(warnings).parseConfiguration(is);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, new DefaultShellCallback(true), warnings);
        myBatisGenerator.generate(null);
    }

}
