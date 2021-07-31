package beSen.filter;

/**
 * @author 康盼Java开发工程师
 */
public interface FilterRegistry {

    /**
     * getChain
     *
     * @param index
     * @return filter
     */
    Filter getChain(int index);
}
