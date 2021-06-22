package beSen.bean.model;

import beSen.bean.BeanInfo;
import beSen.bean.PropertyDescriptor;
import beSen.bean.SimpleBeanInfo;

import java.util.stream.Stream;

public class BeanModel {

    private String beanId;
    private String beanName;

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "{" +
                "beanId:'" + beanId + '\'' +
                "; beanName:'" + beanName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        SimpleBeanInfo simpleBeanInfo = new SimpleBeanInfo(BeanModel.class);
        PropertyDescriptor[] propertyDescriptors = simpleBeanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);
    }
}
