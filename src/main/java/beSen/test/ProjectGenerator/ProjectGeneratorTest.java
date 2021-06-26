package beSen.test.ProjectGenerator;

import beSen.generatorProject.model.ApplicationInfo;
import beSen.generatorProject.model.ProjectInfo;
import beSen.generatorProject.service.impl.ApplicationGenerator;
import beSen.generatorProject.service.impl.PomGenerator;
import beSen.generatorProject.service.impl.ProjectGeneratorImpl;
import org.junit.Test;

public class ProjectGeneratorTest {


    @Test
    public void pomGenerator() throws Exception {
        ProjectInfo projectInfo = new ProjectInfo(
                "org.example",
                "generator",
                "1.0-SNAPSHOT",
                "generator",
                "Demo project for Spring Boot"
        );
        PomGenerator pomGenerator = new PomGenerator(projectInfo);
        pomGenerator.generator();
    }

    @Test
    public void applicationGenerator() throws Exception {
        ApplicationInfo applicationInfo = new ApplicationInfo("beSen.generatorProject.generator", "BeSenApplicationGenerator");
        ApplicationGenerator applicationGenerator = new ApplicationGenerator(applicationInfo);
        applicationGenerator.generator();
    }

    @Test
    public void projectGenerator() throws Exception {
        ProjectInfo projectInfo = new ProjectInfo(
                "org.example",
                "generator",
                "1.0-SNAPSHOT",
                "generator",
                "Demo project for Spring Boot"
        );
        PomGenerator pomGenerator = new PomGenerator(projectInfo);
        ApplicationInfo applicationInfo = new ApplicationInfo("beSen.generatorProject.generator", "BeSenApplicationGenerator");
        ApplicationGenerator applicationGenerator = new ApplicationGenerator(applicationInfo);
        ProjectGeneratorImpl projectGenerator = new ProjectGeneratorImpl(pomGenerator,applicationGenerator);
        projectGenerator.generator();
    }
}
