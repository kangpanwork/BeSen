package beSen.generatorProject.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 因为汽车不能单独制造出来，需要一个一个零件来构造
 * 制造了什么零件，这里就会自动组装
 */
@Component
public class Car implements IGenerator {

    /**
     * 感知，只要有 IGenerator 的实现类，零件自动组装
     * 所以编写实现类的时候，只需要实现制造零件的功能就行，无需关心是怎么组装的。
     */
    @Autowired(required = false)
    private List<IGenerator> list;

    @Override
    public void get() {
        list.stream().forEach(IGenerator::get);
        System.out.println("各个零件进行组装");
    }
}
