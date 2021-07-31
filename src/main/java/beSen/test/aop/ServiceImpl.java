package beSen.test.aop;

/**
 * @author 康盼Java开发工程师
 */
public class ServiceImpl implements IService{

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override public String getName() {
        System.out.println("get name...");
        return name;
    }
}
