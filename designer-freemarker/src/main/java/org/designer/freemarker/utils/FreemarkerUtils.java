package org.designer.freemarker.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/4 23:20
 */
public class FreemarkerUtils {

    private static final String TEMPLATES_PATH = "classpath:/templates/";

    private static Configuration cfg;

    /**
     * 解析 FreeMarker 模板
     *
     * @param freemarkerPath
     * @param dataModel
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String parse(String freemarkerPath, Map<String, Object> dataModel) throws IOException, TemplateException {
        if (cfg == null) {
            synchronized (FreemarkerUtils.class) {
                cfg = initConfiguration();
            }
        }
        Template temp = cfg.getTemplate(freemarkerPath);
        try (Writer out = new StringWriter()) {
            temp.process(dataModel, out);
            return out.toString();
        }
    }

    /**
     * 初始化 FreeMarker 配置
     *
     * @return
     * @throws IOException
     */
    private static Configuration initConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        // 模板路径
        cfg.setTemplateLoader(new SpringTemplateLoader(new DefaultResourceLoader(), TEMPLATES_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        return cfg;
    }

}
