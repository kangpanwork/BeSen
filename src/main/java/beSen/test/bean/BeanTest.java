package beSen.test.bean;

import beSen.bean.PropertyDescriptor;
import beSen.bean.SimpleBeanInfo;

import beSen.bean.definition.BeanDefinition;
import beSen.bean.definition.BeanReference;
import beSen.bean.definition.PropertyValue;
import beSen.bean.definition.PropertyValues;
import beSen.bean.design.DefaultListableBeanFactory;
import beSen.test.bean.model.StudentDao;
import beSen.test.bean.model.StudentService;
import beSen.test.pdf.model.Student;
import org.junit.Test;

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
        Student student = (Student) defaultListableBeanFactory.getBean("student", "1001", "kp", "男", 28, "127231");
        Student student2 = (Student) defaultListableBeanFactory.getBean("student", "1001", "kp", "男", 29, "127231");
        System.out.println(student);
        System.out.println(student2);
    }

    @Test
    public void testStudentQuery() {
        beSen.bean.factory.impl.DefaultListableBeanFactory defaultListableBeanFactory = new beSen.bean.factory.impl.DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StudentDao.class);
        defaultListableBeanFactory.registerBeanDefinition("studentDao",beanDefinition);
        StudentDao studentDao = (StudentDao) defaultListableBeanFactory.getBean("studentDao");
        // 查询成绩低于75分的同学
        beSen.test.bean.model.Student s4 = new beSen.test.bean.model.Student("小李", 75);
        studentDao.queryScoreLessThanThis(s4).forEach(System.out::println);
    }

    @Test
    public void testStudentQuery2() {
        beSen.bean.factory.impl.DefaultListableBeanFactory defaultListableBeanFactory = new beSen.bean.factory.impl.DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StudentDao.class);
        defaultListableBeanFactory.registerBeanDefinition("studentDao",beanDefinition);
        PropertyValues propertyValues = new PropertyValues();
        beSen.test.bean.model.Student s4 = new beSen.test.bean.model.Student("小李", 75);
        PropertyValue p1 = new PropertyValue("student", s4);
        // PropertyValue p2 = new PropertyValue("studentDao", defaultListableBeanFactory.getBean("studentDao"));
        PropertyValue p2 = new PropertyValue("studentDao", new BeanReference("studentDao"));
        propertyValues.addPropertyValue(p1);
        propertyValues.addPropertyValue(p2);
        defaultListableBeanFactory.registerBeanDefinition("studentService",new BeanDefinition(StudentService.class,propertyValues));
        StudentService studentService = (StudentService) defaultListableBeanFactory.getBean("studentService");
        studentService.queryScoreLessThanThis(s4).forEach(System.out::println);
    }
}

