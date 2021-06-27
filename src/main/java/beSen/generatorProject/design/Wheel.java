package beSen.generatorProject.design;

import org.springframework.stereotype.Component;

/**
 * 汽车的轮子
 */
@Component
public class Wheel extends AbstractGenerator {
    @Override
    public void get() {
        tool();
        System.out.println("制造了汽车的轮子");
    }
}
