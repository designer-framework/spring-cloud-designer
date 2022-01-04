package org.designer.freemarker.controller;

import org.designer.freemarker.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/4 22:23
 */
@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @RequestMapping("/download/pdf")
    public void pdf() {
        pdfService.exportPdfByFreeMarker("example.pdf");
    }

}
