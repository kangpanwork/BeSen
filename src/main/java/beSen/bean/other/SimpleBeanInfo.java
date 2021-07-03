package beSen.bean.other;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

public class SimpleBeanInfo implements BeanInfo {

    private Class<?> cls;

    public SimpleBeanInfo(Class cls) {
        this.cls = cls;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        Field[] fields = cls.getDeclaredFields();
        int len = fields.length;
        PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[len];
        for (int i = 0; i < len; ++i) {
            PropertyDescriptor p = new PropertyDescriptor();
            p.setName(fields[i].getName());
            p.setPropertyType(fields[i].getType().getSimpleName());
            for (Method method : cls.getDeclaredMethods()) {
                String name = p.getName();
                String t = name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
                String me = method.getName();
                String m = me.substring(3);
                if (m.equals(t)) {
                    String methodType = method.getReturnType().getSimpleName();
                    String methodName = methodType + " " + method.getName();
                    Class<?>[] classes = method.getParameterTypes();
                    for (Class c : classes) {
                        methodName = methodName + "(" + c.getName() + ")";
                    }
                    if (me.contains("get")) {
                        p.setReadMethod(methodName);
                    } else if (me.contains("set")) {
                        p.setWriteMethod(methodName);
                    }
                }
            }
            propertyDescriptors[i] = p;
        }
        return propertyDescriptors;
    }
}
