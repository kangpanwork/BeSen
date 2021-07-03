package beSen.test.bean;

import beSen.bean.other.PropertyDescriptor;
import beSen.bean.other.SimpleBeanInfo;

import beSen.bean.compose.impl.ClassPathXmlApplicationContext;
import beSen.bean.definition.BeanDefinition;
import beSen.bean.definition.BeanReference;
import beSen.bean.definition.PropertyValue;
import beSen.bean.definition.PropertyValues;
import beSen.bean.design.DefaultListableBeanFactory;
import beSen.bean.readerRegistry.impl.XmlBeanDefinitionReader;
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
        defaultListableBeanFactory.registerBeanDefinition("studentDao", beanDefinition);
        StudentDao studentDao = (StudentDao) defaultListableBeanFactory.getBean("studentDao");
        // 查询成绩低于75分的同学
        PropertyValue p1 = new PropertyValue("name", "小李");
        PropertyValue p2 = new PropertyValue("score", "75");
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(p1);
        propertyValues.addPropertyValue(p2);
        BeanDefinition stuBeanDefinition = new BeanDefinition(beSen.test.bean.model.Student.class, propertyValues);
        // beSen.test.bean.model.Student s4 = new beSen.test.bean.model.Student("小李", 75);
        defaultListableBeanFactory.registerBeanDefinition("student", stuBeanDefinition);
        beSen.test.bean.model.Student s4 = (beSen.test.bean.model.Student) defaultListableBeanFactory.getBean("student");
        studentDao.queryScoreLessThanThis(s4).forEach(System.out::println);
    }

    @Test
    public void testStudentQuery2() {
        beSen.bean.factory.impl.DefaultListableBeanFactory defaultListableBeanFactory = new beSen.bean.factory.impl.DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StudentDao.class);
        defaultListableBeanFactory.registerBeanDefinition("studentDao", beanDefinition);

        PropertyValue stuP1 = new PropertyValue("name", "小李");
        PropertyValue stuP2 = new PropertyValue("score", "75");
        PropertyValues stuPro = new PropertyValues();
        stuPro.addPropertyValue(stuP1);
        stuPro.addPropertyValue(stuP2);
        BeanDefinition stuBeanDefinition = new BeanDefinition(beSen.test.bean.model.Student.class, stuPro);
        // beSen.test.bean.model.Student s4 = new beSen.test.bean.model.Student("小李", 75);
        defaultListableBeanFactory.registerBeanDefinition("student", stuBeanDefinition);
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue p1 = new PropertyValue("student", defaultListableBeanFactory.getBean("student"));
        // PropertyValue p2 = new PropertyValue("studentDao", defaultListableBeanFactory.getBean("studentDao"));
        PropertyValue p2 = new PropertyValue("studentDao", new BeanReference("studentDao"));
        propertyValues.addPropertyValue(p1);
        propertyValues.addPropertyValue(p2);
        defaultListableBeanFactory.registerBeanDefinition("studentService", new BeanDefinition(StudentService.class, propertyValues));
        StudentService studentService = (StudentService) defaultListableBeanFactory.getBean("studentService");
        studentService.queryScoreLessThanThis((beSen.test.bean.model.Student)defaultListableBeanFactory.getBean("student")).forEach(System.out::println);
    }

    @Test
    public void testReadXMl() {
        beSen.bean.factory.impl.DefaultListableBeanFactory factory = new beSen.bean.factory.impl.DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        StudentService studentService = (StudentService) factory.getBean("studentService");
        beSen.test.bean.model.Student student = (beSen.test.bean.model.Student)factory.getBean("student");
        System.out.println(student);
        StudentDao studentDao = (StudentDao) factory.getBean("studentDao");
        System.out.println(studentService.queryScoreLessThanThis(student));
    }

    @Test
    public void testXML() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        StudentService studentService = (StudentService) applicationContext.getBean("studentService");
        beSen.test.bean.model.Student student = (beSen.test.bean.model.Student)applicationContext.getBean("student");
        System.out.println(student);
        System.out.println(studentService.queryScoreLessThanThis(student));
    }
}

