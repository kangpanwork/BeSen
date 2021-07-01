package beSen.bean.resource.impl;

import beSen.bean.resource.Resource;
import beSen.bean.resource.ResourceLoader;
import org.springframework.util.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 资源加载器，提供读取资源功能
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
