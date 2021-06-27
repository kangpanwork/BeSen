package beSen.generatorProject.design;

import org.springframework.stereotype.Component;

/**
 * 汽车底盘
 */
@Component
public class Chassis extends AbstractGenerator {
    @Override
    public void get() {
        tool();
        System.out.println("制造了汽车的底盘");
    }
}
