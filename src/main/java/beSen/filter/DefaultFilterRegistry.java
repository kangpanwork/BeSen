package beSen.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public class DefaultFilterRegistry implements FilterRegistry {

    protected List<Filter> chainList = new ArrayList<>();


    @Override
    public Filter getChain(int index) {
        return chainList.get(index);
    }

    public DefaultFilterRegistry addChain(Filter filterChain) {
        chainList.add(filterChain);
        return this;
    }
}
