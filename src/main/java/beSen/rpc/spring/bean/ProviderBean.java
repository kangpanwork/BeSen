package beSen.rpc.spring.bean;

import beSen.rpc.config.ProviderConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 康盼Java开发工程师
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    private transient ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.format("生产者信息=> [接口：%s] [映射：%s] \r\n", nozzle, ref);
    }
}
