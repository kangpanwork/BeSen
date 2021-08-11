package beSen.test.test;

import beSen.bean.beanPostProcessor.Version;

/**
 * @author 康盼Java开发工程师
 */
public interface IVersionService {
    /**
     * getVersion
     *
     * @return
     */
    @Version("V1")
    String getVersion();
}
