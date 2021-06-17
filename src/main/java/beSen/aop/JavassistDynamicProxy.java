package beSen.aop;

import javassist.CtClass;

public interface JavassistDynamicProxy {

    /**
     * 用于创建类的字节码
     */
    CtClass makeClass() throws Exception;

}
