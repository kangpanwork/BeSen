package codec;

/**
 * 序列化 ：对象转化为二进制数组
 *
 * @author 康盼Java开发工程师
 */
public interface Encoder {

    /**
     * 将对象序列化字节
     *
     * @param object
     * @return
     */
    byte[] encode (Object object);

}