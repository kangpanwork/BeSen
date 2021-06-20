package beSen.test.ehcache;

import beSen.ehcache.BsEhcache;
import beSen.ehcache.Ehcache;

/**
 * 业务处理类，业务层跟缓存组件写在一起的话，精密耦合，依赖性强
 * 基于动态代理的缓存解决方案
 */
public class BusinessController {

    private BsEhcache bsEhcache;

    public BusinessController(BsEhcache bsEhcache) {
        this.bsEhcache = bsEhcache;
    }

    /**
     * CGLIB 报这个错的时候 是没有无参构造函数
     * Superclass has no null constructors but no arguments were given
     */
    public BusinessController() {

    }

    /**
     * 业务和缓存组件一起使用
     * 模拟数据库查询 根据注解 ID 查询 value
     */
    public String doBusiness() throws Exception{
        String cache = bsEhcache.get(Ehcache.CACHE_NAME,"ID");
        if (null == cache || cache.isEmpty()) {
            // 模拟数据库查询
            Thread.sleep(1000);
            System.out.println("处理业务逻辑");
            System.out.println("从数据库查询返回");
            bsEhcache.put(Ehcache.CACHE_NAME,"ID","value");
            return "value";
        }
        System.out.println("直接从缓存中返回");
        return cache;
    }

    /**
     * 使用代理模式，业务逻辑和缓存组件分离，在业务层只要关注自己的业务逻辑就行
     */
    public String justDoBusiness() throws Exception {
        // 模拟数据库查询
        Thread.sleep(1000);
        System.out.println("处理业务逻辑");
        System.out.println("从数据库查询返回");
        return "value";
    }

}
