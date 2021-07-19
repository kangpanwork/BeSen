package beSen.test.reflect;

import beSen.reflect.ReflectUtils;
import beSen.spring.model.Coffee;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 */
public class ReflectTest {

    @Test
    public void test() throws Exception {
        Coffee coffee = new Coffee("摩卡",100,100.00,"九折出售");
        ReflectUtils.getValue(coffee,"name");
        ReflectUtils.setValue(coffee,new Object[]{"摩卡1"},"name");
        System.out.println(ReflectUtils.getValue(coffee,"name"));
    }
}
