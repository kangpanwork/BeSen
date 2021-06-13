package beSen.pdf.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * PDF 模板及字体配置
 */
@Configuration
@ConfigurationProperties
@Data
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
}
