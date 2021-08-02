package beSen.test.StringListMap.model;

/**
 * @author 康盼Java开发工程师
 */
public class People {

    private Long id;

    private String name;

    private String sex;

    private int age;

    private int salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public People(Long id, String name, String sex, int age, int salary) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
