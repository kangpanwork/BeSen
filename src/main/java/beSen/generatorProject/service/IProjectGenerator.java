package beSen.generatorProject.service;

import beSen.generatorProject.model.ProjectInfo;

/**
 * 生成项目的接口
 */
public interface IProjectGenerator {

    void generator() throws Exception;

    /**
     * 项目文件位置 + 代码生成的位置
     */
    String generatorFilePath = System.getProperty("user.dir") + "/src/main/Java/beSen/generatorProject/generator";

    /**
     * 项目文件位置 + 模板的位置
     */
    String templateFilePath = System.getProperty("user.dir") +  "/src/main/resources/templates";
}
