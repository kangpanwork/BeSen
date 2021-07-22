package beSen.jwt;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;

/**
 * @author 康盼Java开发工程师
 */
public class Hash {


    public static String encode(String key,String data) {
        String code = null;
        try{
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec =
                    new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            mac.init(secretKeySpec);
            ByteArrayOutputStream byteArrayOutputStream;
            code = Hex.toHexString(mac.doFinal(data.getBytes("UTF-8")));
        } catch(Exception exception) {
            throw new RuntimeException("数据编码错误！！！");
        }
        return code;
    }

}
