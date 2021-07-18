package beSen.test.excel.model;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public class People {

    private String name;
    private String sex;
    private String age;
    private List<String> propertiesName;
    private List<String>  propertiesValue;
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(List<String> propertiesName) {
        this.propertiesName = propertiesName;
    }

    public List<String> getPropertiesValue() {
        return propertiesValue;
    }

    public void setPropertiesValue(List<String> propertiesValue) {
        this.propertiesValue = propertiesValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public People() {
    }

    public People(String name, String sex, String age, List<String> propertiesName, List<String> propertiesValue, String remark) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.propertiesName = propertiesName;
        this.propertiesValue = propertiesValue;
        this.remark = remark;
    }


}
