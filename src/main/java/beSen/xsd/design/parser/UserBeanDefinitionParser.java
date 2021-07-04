package beSen.xsd.design.parser;

import beSen.xsd.design.model.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.lang.Nullable;
import org.w3c.dom.Element;

/**
 * 用来解析XSD文件中的定义和组件定义
 *
 * @author 康盼Java开发工程师
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");
        if (hasText(userName)) {
            builder.addPropertyValue("userName",userName);
        }
        if (hasText(email)) {
            builder.addPropertyValue("email",email);
        }
    }

    private static boolean hasText(@Nullable String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
