package beSen.ehcache;



/**
 * @author 康盼Java开发工程师
 */
public interface Ehcache {
    String CACHE_NAME = "StringCache";

    /**
     * put cache
     *
     * @param cacheName
     * @param key
     * @param value
     */
    void put(String cacheName, String key, String value);

    /**
     * get cache
     *
     * @param cacheName
     * @param key
     * @return
     */
    String get(String cacheName, String key);
}
