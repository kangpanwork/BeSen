package beSen.bean.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口 获取输入流
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
