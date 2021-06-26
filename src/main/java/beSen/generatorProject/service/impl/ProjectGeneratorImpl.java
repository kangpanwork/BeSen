package beSen.generatorProject.service.impl;

import beSen.generatorProject.service.IProjectGenerator;


/**
 * 项目生成，包括pom文件的生成，启动类生成等
 */
public class ProjectGeneratorImpl implements IProjectGenerator {

    private IProjectGenerator[] projectGenerators;

    public ProjectGeneratorImpl(IProjectGenerator... projectGenerators) {
        this.projectGenerators = projectGenerators;
    }

    @Override
    public void generator() throws Exception {
        for (int i = 0; i < projectGenerators.length; ++i) {
            projectGenerators[i].generator();
        }
    }

}
