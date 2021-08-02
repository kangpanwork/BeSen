package beSen.future.simpleDesign;

/**
 * @author 康盼Java开发工程师
 */
public class FutureMain {
    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("请求数据");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){}
        System.out.println("数据=" + data.getResult());
    }
}
