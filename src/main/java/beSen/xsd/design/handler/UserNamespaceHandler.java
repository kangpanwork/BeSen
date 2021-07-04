package beSen.xsd.design.handler;

import beSen.xsd.design.parser.UserBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 注册信息
 *
 * @author 康盼Java开发工程师
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
