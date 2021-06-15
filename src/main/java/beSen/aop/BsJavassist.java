package beSen.aop;

import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

/**
 * 用于 Javassist 创建 class 字节码
 */
public class BsJavassist {
    /**
     * 类名
     */
    private String className;
    /**
     * 超类
     */
    private CtClass ctClass;

    /**
     * 构造函数
     */
    private CtConstructor ctConstructor;

    /**
     * 属性
     */
    private CtField ctField;

    /**
     * 方法
     */
    private CtMethod ctMethod;

    /**
     * 方法名称
     */
    private String methodName;

    private String insertBefore;

    private String insertAfter;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CtClass getCtClass() {
        return ctClass;
    }

    public void setCtClass(CtClass ctClass) {
        this.ctClass = ctClass;
    }

    public CtConstructor getCtConstructor() {
        return ctConstructor;
    }

    public void setCtConstructor(CtConstructor ctConstructor) {
        this.ctConstructor = ctConstructor;
    }

    public CtField getCtField() {
        return ctField;
    }

    public void setCtField(CtField ctField) {
        this.ctField = ctField;
    }

    public CtMethod getCtMethod() {
        return ctMethod;
    }

    public void setCtMethod(CtMethod ctMethod) {
        this.ctMethod = ctMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getInsertBefore() {
        return insertBefore;
    }

    public void setInsertBefore(String insertBefore) {
        this.insertBefore = insertBefore;
    }

    public String getInsertAfter() {
        return insertAfter;
    }

    public void setInsertAfter(String insertAfter) {
        this.insertAfter = insertAfter;
    }
}
