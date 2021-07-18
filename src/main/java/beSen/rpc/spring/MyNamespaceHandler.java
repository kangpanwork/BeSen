package beSen.rpc.spring;

import beSen.rpc.spring.bean.ProviderBean;
import beSen.rpc.spring.bean.ServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author 康盼Java开发工程师
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport  {

    @Override
    public void init() {
        registerBeanDefinitionParser("server", new MyBeanDefinitionParser(ServerBean.class));
        registerBeanDefinitionParser("provider",new MyBeanDefinitionParser(ProviderBean.class));
    }
}
