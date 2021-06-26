package beSen.generatorProject.service.impl;

import beSen.generatorProject.service.IProjectGenerator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 生成模板的公共功能
 */
public abstract class AbstractProjectGenerator implements IProjectGenerator {

    private static String ENCODING = "UTF-8";

    private static Configuration cfg;

    static {
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_23);
            File file = new File( templateFilePath);
            cfg.setDirectoryForTemplateLoading(file);
            cfg.setDefaultEncoding(ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据模板文件生成ftl模板
     *
     * @param ftlPath 模板路径
     * @return Template
     * @throws IOException
     */
    private Template getTemplate(String ftlPath) throws IOException {
        return cfg.getTemplate(ftlPath, ENCODING);
    }

    /**
     * 根据模板写入文件
     *
     * @param file     写入的文件
     * @param ftlPath  ftl模板路径
     * @param ftlModel ftl模板数据模型
     * @throws IOException
     * @throws TemplateException
     */
    protected void writeFile(File file, String ftlPath, Object ftlModel) throws IOException, TemplateException {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file))) {
            getTemplate(ftlPath).process(ftlModel, outputStreamWriter);
        }
    }
}
