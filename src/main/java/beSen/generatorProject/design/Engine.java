package beSen.generatorProject.design;

import org.springframework.stereotype.Component;

/**
 * 汽车的引擎
 */
@Component
public class Engine extends AbstractGenerator {
    @Override
    public void get() {
        tool();
        System.out.println("制造了汽车的引擎");
    }
}
