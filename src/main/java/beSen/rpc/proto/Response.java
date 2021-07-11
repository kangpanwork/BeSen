package beSen.rpc.proto;


import org.eclipse.jetty.http.HttpStatus;

/**
 * 表示一个服务的响应
 *
 * @author 康盼Java开发工程师
 */
public class Response {
    /**
     * 默认成功
     */
    private int code = HttpStatus.OK_200;
    private String message = "ok";
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
