package beSen.coderc.impl;

import beSen.coderc.Decoder;
import com.alibaba.fastjson.JSON;

/**
 * @author 康盼Java开发工程师
 */
public class SimpleJsonDecoder implements Decoder {

    @Override
    public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes,tClass);
    }
}
