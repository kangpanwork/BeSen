package beSen.test.bean;

import beSen.bean.PropertyDescriptor;
import beSen.bean.SimpleBeanInfo;
import beSen.bean.model.BeanModel;
import org.junit.Test;

import java.util.stream.Stream;

public class BeanTest {

    @Test
    public void getBeanInfo() {
        SimpleBeanInfo simpleBeanInfo = new SimpleBeanInfo(BeanModel.class);
        PropertyDescriptor[] propertyDescriptors = simpleBeanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);
    }
}

