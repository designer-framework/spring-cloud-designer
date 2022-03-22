package org.designer.mybatis.unit;

import org.designer.mybatis.utils.MybatisUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        config.getContexts().forEach(context -> {
            List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
            tableConfigurations.forEach(tableConfiguration -> {
                tableConfiguration.setTableName(
                        String.format(tableConfiguration.getTableName(), MybatisUtils.getFormatProperty(context, "modelName"))
                );
            });
        });
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, new DefaultShellCallback(true), warnings);
        myBatisGenerator.generate(null);
    }

}
