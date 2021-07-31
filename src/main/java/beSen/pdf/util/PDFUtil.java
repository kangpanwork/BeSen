package beSen.pdf.util;

import beSen.resource.util.ResourceFileUtil;
import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 描述: PDF 导出工具类
 * @author 康盼Java开发工程师
 */
public final class PDFUtil {
    private PDFUtil() {
    }

    private volatile static Configuration configuration;

    static {
        if (configuration == null) {
            synchronized (PDFUtil.class) {
                if (configuration == null) {
                    configuration = new Configuration(Configuration.VERSION_2_3_28);
                    configuration.setDefaultEncoding("UTF-8");
                    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                    configuration.setLogTemplateExceptions(false);
                    configuration.setWrapUncheckedExceptions(true);
                }
            }
        }
    }

    /**
     * freemarker 引擎渲染 html
     *
     * @param dataMap     数据来源
     * @param ftlFilePath 模板文件相对路径(相对于 resources路径,路径 + 文件名) eg: "templates/pdf_export_demo.ftl"
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String freemarkerRender(Map<String, List<?>> dataMap, String ftlFilePath) throws IOException, TemplateException {
        try (Writer out = new StringWriter()) {
            configuration.setDirectoryForTemplateLoading(new File(ResourceFileUtil.use().getParent(ftlFilePath)));
            Template template = configuration.getTemplate(ResourceFileUtil.use().getFileName(ftlFilePath));
            template.process(dataMap, out);
            out.flush();
            return out.toString();
        }
    }

    /**
     * 使用 iText 生成 PDF 文档
     *
     * @param htmlStr      模板解析后的 html 字符串
     * @param fontFilePath 字体文件路径 (相对路径 + 文件名)
     * @return
     */
    public static byte[] createPdf(String htmlStr, String fontFilePath) throws IOException, DocumentException {
        byte[] result;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlStr);
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont(ResourceFileUtil.use().getAbsolutePath(fontFilePath), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.layout();
            renderer.createPDF(outputStream);
            result = outputStream.toByteArray();
            outputStream.flush();
        }
        return result;
    }
}
