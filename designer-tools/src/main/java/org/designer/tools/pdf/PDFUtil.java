package org.designer.tools.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.designer.tools.exception.ToolsException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/4 23:11
 */
public class PDFUtil {

    /**
     * 导出到指定 PDF 路径
     *
     * @param html
     * @param pdfOutputStream
     * @param fonts
     * @throws DocumentException
     * @throws IOException
     */
    public static void exportPDF(String html, OutputStream pdfOutputStream, String... fonts) throws ToolsException {
        ITextRenderer renderer = new ITextRenderer();
        try (OutputStream os = pdfOutputStream) {
            addFont(renderer, fonts);
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
        } catch (IOException | DocumentException e) {
            throw new ToolsException("pdf导出失败", e);
        }
    }

    /**
     * 加载字体
     *
     * @param renderer
     * @param fonts
     * @throws DocumentException
     * @throws IOException
     */
    private static void addFont(ITextRenderer renderer, String... fonts) throws DocumentException, IOException {
        ITextFontResolver fontResolver = renderer.getFontResolver();
        for (String font : fonts) {
            fontResolver.addFont(font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
    }

}
