package beSen.rpc.example;

/**
 * @author 康盼Java开发工程师
 */

public enum ServiceEnum {
    CALC_SERVICE(CalcService.class.getName(),new CalcServiceImpl());
    private String clazzName;
    private Object target;

    ServiceEnum(String clazzName, Object target) {
        this.clazzName = clazzName;
        this.target = target;
    }

    public static Object getTarget(String className) {
        for(ServiceEnum serviceEnum : ServiceEnum.values()) {
            if (className.equals(serviceEnum.clazzName)) {
                return serviceEnum.target;
            }
        }
        return null;
    }

}
