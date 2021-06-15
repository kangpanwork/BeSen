package beSen.aop;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class BsJavassistDynamicProxy implements JavassistDynamicProxy {

    private BsJavassist bsJavassist;

    private final ClassPool classPool;

    public BsJavassistDynamicProxy(BsJavassist bsJavassist)  {
        this.bsJavassist = bsJavassist;
        classPool = ClassPool.getDefault();
    }

    @Override
    public CtClass get(String className) throws Exception {
       CtClass ctClass = classPool.get(bsJavassist.getClassName());
       // 对原有的方法进行插入
       CtMethod ctMethod = ctClass.getDeclaredMethod(bsJavassist.getMethodName());
       ctMethod.insertBefore(bsJavassist.getInsertBefore());
       ctMethod.insertAfter(bsJavassist.getInsertAfter());


        return null;
    }

    @Override
    public CtClass makeClass(String className) {
        return null;
    }
}
