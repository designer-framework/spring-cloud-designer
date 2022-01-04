package org.designer.freemarker.service;

import freemarker.template.TemplateException;
import org.designer.freemarker.utils.FreemarkerUtils;
import org.designer.tools.pdf.PDFUtil;
import org.designer.tools.web.WebUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/4 22:20
 */
@Service
public class PdfService {

    public void exportPdfByFreeMarker(String fileName) {
        WebUtils.export(fileName, outputStream -> {
            try {
                // 数据模型
                Map<String, Object> dataModel = new HashMap<>(4);
                dataModel.put("author", "刘泽龙");
                PDFUtil.exportPDF(
                        FreemarkerUtils.parse("pdf-example.ftl", dataModel)
                        , outputStream
                        , getFonts()
                );
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        });
    }


    private String getFonts() {
        return new ClassPathResource("fonts/msyh.ttc").getPath();
    }

}
