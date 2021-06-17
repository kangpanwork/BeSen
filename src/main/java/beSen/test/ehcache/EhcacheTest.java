package beSen.test.ehcache;

import beSen.ehcache.BsEhcache;
import beSen.ehcache.BusinessCacheController;
import beSen.ehcache.BusinessController;
import org.junit.Test;

public class EhcacheTest {


    @Test
    public void testDoBusiness() throws Exception{
        BsEhcache bsEhcache = new BsEhcache();
        BusinessController businessController = new BusinessController(bsEhcache);
        System.out.println(businessController.doBusiness());
    }

    @Test
    public void testJustDoBusiness() throws Exception{
        BsEhcache bsEhcache = new BsEhcache();
        BusinessCacheController businessCacheController = new BusinessCacheController(bsEhcache);
        BusinessController businessController =businessCacheController.getBusinessController();
        System.out.println(businessController.justDoBusiness());
    }
}
