package beSen.rpc.example;

/**
 * @author 康盼Java开发工程师
 */
public class CalcServiceImpl implements CalcService {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
