package beSen.test.filter;


/**
 * @author 康盼Java开发工程师
 */
public class Log implements IMessage{
    private String message;

    public Log(String message) {
        this.message = message;
    }

    @Override
    public String getLog() {
        System.out.println(message);
        return message;
    }
}
