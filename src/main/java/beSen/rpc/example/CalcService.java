package beSen.rpc.example;

/**
 * @author 康盼Java开发工程师
 */
public interface CalcService {

    /**
     * 两个数相加
     *
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b);

    /**
     * 两个数相减
     *
     * @param a
     * @param b
     * @return
     */
    int minus(int a, int b);

}
