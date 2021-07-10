package beSen.aop;


/**
 * 用于 Javassist 创建 class 字节码
 * @author 康盼Java开发工程师
 */
public class BsJavassist {
    /**
     * 类名
     */
    private String className;

    /**
     * 实现的接口名
     */
    private String interfaces;

    /**
     * 属性
     */
    private String field;


    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法体
     */
    private String body;

    private String insertBefore;

    private String insertAfter;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String interfaces) {
        this.interfaces = interfaces;
    }
}
