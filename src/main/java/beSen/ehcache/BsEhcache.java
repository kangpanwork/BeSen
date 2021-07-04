package beSen.ehcache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class BsEhcache implements Ehcache {

    private CacheManager cacheManager;

    /**
     * 四层模型
     *
     * heap 堆缓存，受到jvm的管控，速度最快
     * off-heap 堆外缓存，不受jvm的管控，受到RAM的限制
     * disk 磁盘存储，尽量使用高性能的SSD 这一层的存储，不能在不同的CacheManager之间共享
     * clustered 群集存储-该数据存储是远程服务器上的缓存
     */
    public BsEhcache() {
        this.cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                .withCache(CACHE_NAME,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES)
                        .offheap(1, MemoryUnit.MB)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20)))).build();
        cacheManager.init();
    }


    @Override
    public void put(String cacheName, String key, String value) {
        cacheManager.getCache(cacheName,String.class,String.class).put(key,value);

    }

    @Override
    public String get(String cacheName, String key) {
        return cacheManager.getCache(cacheName,String.class,String.class).get(key);
    }

}
