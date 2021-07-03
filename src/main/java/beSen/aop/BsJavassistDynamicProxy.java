package beSen.aop;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;


/**
 * @author 康盼Java开发工程师
 */
public class BsJavassistDynamicProxy implements JavassistDynamicProxy {

    private BsJavassist bsJavassist;

    private final ClassPool classPool;


    public BsJavassistDynamicProxy(BsJavassist bsJavassist) {
        this.bsJavassist = bsJavassist;
        classPool = ClassPool.getDefault();
    }

    /**
     * makeClass
     *
     * @return
     * @throws Exception
     */
    @Override
    public CtClass makeClass() throws Exception {
        CtClass ctClass = classPool.makeClass(bsJavassist.getClassName());
        // 添加父类接口
        ctClass.addInterface(classPool.get(bsJavassist.getInterfaces()));
        String field = bsJavassist.getField();
        CtField ctField = CtField.make(field, ctClass);
        ctClass.addField(ctField);
        // 默认构造函数
        ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));
        // 添加方法体
        CtMethod ctMethod = CtNewMethod.make(bsJavassist.getBody(), ctClass);
        ctClass.addMethod(ctMethod);
        return ctClass;
    }

}
