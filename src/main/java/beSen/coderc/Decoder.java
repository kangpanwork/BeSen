package beSen.coderc;

/**
 * 反序列化 ：二进制数组转对象
 *
 * @author 康盼Java开发工程师
 */
public interface Decoder {

    /**
     * 通过类型序列化对象
     *
     * @param bytes
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes,Class<T> tClass);

}
