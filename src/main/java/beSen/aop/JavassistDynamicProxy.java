package beSen.aop;

import javassist.CtClass;

public interface JavassistDynamicProxy {
    /**
     * 获取原有类字节码
     *
     * @param className
     * @return
     */
    CtClass get(String className) throws Exception;

    /**
     * 用于创建类的字节码
     *
     * @param className
     * @return
     */
    CtClass makeClass(String className) throws Exception;


}
