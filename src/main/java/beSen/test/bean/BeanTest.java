package beSen.test.bean;

import beSen.bean.PropertyDescriptor;
import beSen.bean.SimpleBeanInfo;

import beSen.bean.design.BeanFactory;
import beSen.bean.design.DefaultListableBeanFactory;
import beSen.test.pdf.model.Student;
import org.junit.Test;

import java.sql.Struct;
import java.util.stream.Stream;

public class BeanTest {

    @Test
    public void getBeanInfo() {
        SimpleBeanInfo simpleBeanInfo = new SimpleBeanInfo(BeanTest.class);
        PropertyDescriptor[] propertyDescriptors = simpleBeanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);
    }

    @Test
    public void getBean() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("student", Student.class);
        Student student = (Student) defaultListableBeanFactory.getBean("student","1001","kp","男",28,"127231");
        Student student2 = (Student) defaultListableBeanFactory.getBean("student","1001","kp","男",29,"127231");
        System.out.println(student);
        System.out.println(student2);

    }
}

