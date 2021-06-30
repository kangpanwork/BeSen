package beSen.bean.resource.impl;

import beSen.bean.resource.Resource;
import beSen.bean.resource.ResourceLoader;
import beSen.bean.resource.impl.ClassPathResource;
import beSen.bean.resource.impl.FileSystemResource;
import beSen.bean.resource.impl.UrlResource;
import org.springframework.util.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认资源加载器
 */
public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
