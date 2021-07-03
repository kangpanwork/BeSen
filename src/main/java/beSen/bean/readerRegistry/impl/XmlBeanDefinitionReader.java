package beSen.bean.readerRegistry.impl;


import beSen.bean.definition.BeanDefinition;
import beSen.bean.definition.BeanReference;
import beSen.bean.definition.PropertyValue;
import beSen.bean.registry.BeanDefinitionRegistry;
import beSen.bean.resource.Resource;
import beSen.bean.resource.ResourceLoader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * 组合了AbstractBeanDefinitionReader类的功能，提供XML解析， 注册 BeanDefinition
 * @author 康盼Java开发工程师
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 核心暴露方法，根据xml地址获取
     *
     * @param location
     */
    @Override
    public void loadBeanDefinitions(String location) {
        // 获取的是父类的DefaultResourceLoader，构造的时候初始化了
        Resource resource = getResourceLoader().getResource(location);
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 加载流，解析bean信息，注册到 bean BeanDefinition
     *
     * @param inputStream
     */
    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document doc = XmlUtil.readXML(inputStream);
        Element element = doc.getDocumentElement();
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            // 判断是不是元素
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }

            // 判断是不是对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            //      <?xml version="1.0" encoding="UTF-8"?>
            //      <beans>
            //      这里也会解析，不是元素直接跳过
            //      <bean id="userDao" class="UserDao"/>
            //      这里也会解析，不是元素直接跳过
            //      <bean id="userService" class="UserService">
            //        <property name="" value=""/>
            //        <property name="" ref="userDao"/>
            //      </bean>
            //      这里也会解析，不是元素直接跳过
            //      </beans>
            // 开始解析
            Element ele = (Element) childNodes.item(i);
            String id = ele.getAttribute("id");
            String name = ele.getAttribute("name");
            String className = ele.getAttribute("class");

            try {
                Class<?> clazz = Class.forName(className);
                // <bean id="userDao" class="UserDao"/> 就取id
                // <bean name="userDao" class="UserDao"/> 就取name
                // <bean class="UserDao"/> 就取class
                String beanName = StrUtil.isNotEmpty(id) ? id : name;
                if (StrUtil.isEmpty(beanName)) {
                    beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                }
                setPropertyValue(ele, clazz, beanName);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }

    }

    /**
     * property赋值，用于bean信息注册使用
     *
     * @param ele
     */
    private void setPropertyValue(Element ele, Class clazz, String beanName) {
        BeanDefinition beanDefinition = new BeanDefinition(clazz);
        NodeList childNodes = ele.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            if (!"property".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            // 开始解析
            Element e = (Element) childNodes.item(i);
            String name = e.getAttribute("name");
            String value = e.getAttribute("value");
            String ref = e.getAttribute("ref");

            //        <property name="" value=""/>
            //        <property name="" ref="userDao"/>
            // ref 处理  有些是没有带ref的
            Object beanValue = StrUtil.isNotEmpty(ref) ? new BeanReference(ref) : value;

            // 赋值
            PropertyValue propertyValue = new PropertyValue(name, beanValue);
            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
        }
        getRegistry().registerBeanDefinition(beanName, beanDefinition);

    }
}
