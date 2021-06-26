package beSen.generatorProject.service.impl;

import beSen.generatorProject.model.ProjectInfo;
import beSen.generatorProject.service.IProjectGenerator;

import java.io.File;

/**
 * pom 文件生成
 */
public class PomGenerator extends AbstractProjectGenerator implements IProjectGenerator {

    private ProjectInfo projectInfo;

    public PomGenerator(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    @Override
    public void generator() throws Exception {
        // pom 文件位置
        File file = new File(generatorFilePath,"pom.xml");
        writeFile(file, "pom.ftl", projectInfo);
    }
}
