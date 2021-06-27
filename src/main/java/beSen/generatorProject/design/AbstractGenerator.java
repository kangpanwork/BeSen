package beSen.generatorProject.design;

/**
 * 制作汽车所必备的工具，这里是所有制造汽车零件都需要的功能
 */
public abstract class AbstractGenerator implements IGenerator {

    protected void tool() {
        System.out.println("使用工具制造汽车零件");
    }

}
