package beSen.reflect;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.MethodInvoker;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 反射工具类
 *
 * @author 康盼Java开发工程师
 */
public class ReflectUtils {

    /**
     * 根据class创建对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 根据class获取所有公共方法
     *
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz) {
        // 获取当前类的所有方法，含private
        Method[] declaredMethods = clazz.getDeclaredMethods();
        // 筛选 public 方法
        List<Method> result = Arrays.stream(declaredMethods).filter(method -> Modifier.isPublic(method.getModifiers())).collect(Collectors.toList());
        return result.toArray(new Method[0]);



    }

    /**
     * 反射方法调用
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object object, Method method, Object... args) {
        try {
            // 当方法为静态方法时，object为null，因为静态方法存在于类上，而非随对象产生
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * setValue
     *
     * @param target
     * @param args
     * @param fieldName
     * @throws Exception
     */
    public static void setValue(Object target,Object[] args,String fieldName) throws Exception{
        MethodInvoker methodInvoker = new MethodInvoker();
        methodInvoker.setTargetObject(target);
        methodInvoker.setArguments(args);
        String methodName = "set" + StrUtil.upperFirst(fieldName);
        methodInvoker.setTargetMethod(methodName);
        methodInvoker.prepare();
        methodInvoker.invoke();
    }

    /**
     * getValue
     *
     * @param target
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static Object getValue(Object target,String fieldName) throws Exception {
        MethodInvoker methodInvoker = new MethodInvoker();
        methodInvoker.setTargetObject(target);
        methodInvoker.setTargetClass(target.getClass());
        String methodName = "get" + StrUtil.upperFirst(fieldName);
        Field field = target.getClass().getDeclaredField(fieldName);
        field = ReflectionUtils.findField(target.getClass(),fieldName);
        methodInvoker.setTargetMethod(methodName);
        methodInvoker.prepare();
        Object value = methodInvoker.invoke();
        // NumberUtil
        if (value instanceof String && field.getGenericType().equals(String.class)) {
            return value;
        }
        return value;

    }
}
