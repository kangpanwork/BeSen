package codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的序列化实现
 *
 * @author 康盼Java开发工程师
 */
public class JsonEncoder implements Encoder {

    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}
