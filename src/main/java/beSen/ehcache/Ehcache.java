package beSen.ehcache;


import java.io.Serializable;

public interface Ehcache {
    String CACHE_NAME = "StringCache";
    void put(String cacheName, String key, String value);
    String get(String cacheName, String key);
}
