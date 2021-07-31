package beSen.pdf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * PDF 模板及字体配置
 * @author 康盼Java开发工程师
 */
@Configuration
@ConfigurationProperties
public class PdfExportConfig {

    /**
     * 字体文件相对路径
     */
    @Value("${pdfExport.font}")
    private String font;

    /**
     * 导出模板文件相对路径
     */
    @Value("${pdfExport.ftlTemplate}")
    private String fltTemplate;

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFltTemplate() {
        return fltTemplate;
    }

    public void setFltTemplate(String fltTemplate) {
        this.fltTemplate = fltTemplate;
    }
}
