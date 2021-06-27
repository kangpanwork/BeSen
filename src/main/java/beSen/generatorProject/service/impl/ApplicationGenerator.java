package beSen.generatorProject.service.impl;

import beSen.generatorProject.model.ApplicationInfo;
import beSen.generatorProject.service.IProjectGenerator;

import java.io.File;

public class ApplicationGenerator extends AbstractProjectGenerator {

    private ApplicationInfo applicationInfo;

    public ApplicationGenerator(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    @Override
    public void generator() throws Exception {
        // 启动类文件位置
        File file = new File(generatorFilePath, applicationInfo.getClassName() + ".java");
        writeFile(file, "application.ftl", applicationInfo);
    }
}
