package org.designer.freemarker.service;

import freemarker.template.TemplateException;
import lombok.Data;
import org.designer.freemarker.utils.FreemarkerUtils;
import org.designer.tools.pdf.PDFUtil;
import org.designer.tools.web.WebUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
                PDFUtil.exportPDF(
                        FreemarkerUtils.parse("pdf-example.ftl", new OutData())
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

    @Data
    public static class OutData {
        private String author = "author";
        private String one = "1";
        private String two = "2";
        private String three = "3";
        private String curr = "12";
        private List<Data_> detailList = Arrays.asList(new Data_());
    }

    @Data
    public static class Data_ {

        private String column1;
        private String column2;
        private String column3;
        private String column4;
        private String column5;

        public Data_() {
            column1 = UUID.randomUUID().toString();
            column2 = UUID.randomUUID().toString();
            column3 = UUID.randomUUID().toString();
            column4 = UUID.randomUUID().toString();
            column5 = UUID.randomUUID().toString();
        }
    }

}
