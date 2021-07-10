package codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的反序列化实现
 *
 * @author 康盼Java开发工程师
 */
public class JsonDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes, tClass);
    }
}
