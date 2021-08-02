package beSen.future.simpleDesign;

/**
 * @author 康盼Java开发工程师
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String result) {
        try {
            System.out.println("非常耗时的操作");
            Thread.sleep(2000);
        } catch (InterruptedException e){}
        this.result = result;
    }

    @Override
    public String getResult() {
        return result;
    }
}
