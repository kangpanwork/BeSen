package beSen.aop;

import javassist.CtClass;

/**
 * @author 康盼Java开发工程师
 */
public interface JavassistDynamicProxy {

    /**
     * 用于创建类的字节码
     *
     * @return
     * @throws Exception
     */
    CtClass makeClass() throws Exception;

}
