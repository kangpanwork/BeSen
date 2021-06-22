package beSen.bean;

/**
 * bean 属性描述
 */
public class PropertyDescriptor {
    /**
     * 属性名称 eg:name=beanId;
     */
    private String name;
    /**
     * 属性类型 eg:propertyType=java.lang.String;
     */
    private String propertyType;
    /**
     * 提供可读的方法 eg:readMethod=public java.lang.String beSen.bean.model.BeanModel.getBeanId();
     */
    private String readMethod;
    /**
     * 提供可写的方法 eg:writeMethod=public void beSen.bean.model.BeanModel.setBeanId(java.lang.String)
     */
    private String writeMethod;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getReadMethod() {
        return readMethod;
    }

    public void setReadMethod(String readMethod) {
        this.readMethod = readMethod;
    }

    public String getWriteMethod() {
        return writeMethod;
    }

    public void setWriteMethod(String writeMethod) {
        this.writeMethod = writeMethod;
    }

    @Override
    public String toString() {
        return "PropertyDescriptor{" +
                "name='" + name + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", readMethod='" + readMethod + '\'' +
                ", writeMethod='" + writeMethod + '\'' +
                '}';
    }
}
