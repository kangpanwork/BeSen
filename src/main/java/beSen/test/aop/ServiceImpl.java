package beSen.test.aop;

public class ServiceImpl implements IService{

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        System.out.println("get name...");
        return name;
    }
}
