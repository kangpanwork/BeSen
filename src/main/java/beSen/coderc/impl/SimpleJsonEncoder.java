package beSen.coderc.impl;

import beSen.coderc.Encoder;
import com.alibaba.fastjson.JSON;

/**
 * @author 康盼Java开发工程师
 */
public class SimpleJsonEncoder implements Encoder {

    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}
