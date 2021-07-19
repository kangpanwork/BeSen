package beSen.spring.valid;

import cn.hutool.core.util.StrUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * @author 康盼Java开发工程师
 */
public class ValidExceptionUtil {

    /**
     * 使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度
     */
    private static final Pattern pattern = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    private static final String STRING = "java.lang.String";

    private static final String INT = "int";

    private static final String DOUBLE = "double";

    /**
     * 获取参数及自定义的校验信息
     *
     * @param hasError      是否有校验的错误
     * @param bindingResult bindingResult
     */
    public static void validation(boolean hasError, BindingResult bindingResult) {
        String errorInfo = "[%s: %s]; ";
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String info = String.format(Locale.ROOT, errorInfo, fieldError.getField(), fieldError.getDefaultMessage());
            stringBuilder.append(info);
        }
        String exceptionInfo = stringBuilder.toString();
        if (hasError) {
            throw new ValidationException(exceptionInfo);
        }
    }

    public static <T> void validField(T t) {
        AtomicInteger hasError = new AtomicInteger();
        String errorInfo = "[parameter %s can not be null]; ";
        StringBuilder stringBuilder = new StringBuilder();
        Method[] methods = t.getClass().getDeclaredMethods();
        Arrays.stream(methods).filter(method ->
                pattern.matcher(method.getName()).matches()
                        && Modifier.isPublic(method.getModifiers())
                        && method.getParameterTypes().length == 0
        ).forEach(method -> {
            try {
                String filedName = StrUtil.lowerFirst(method.getName().substring(3));
                Field field = t.getClass().getDeclaredField(filedName);
                Valid fieldAnnotation = field.getAnnotation(Valid.class);
                Valid methodAnnotation = method.getAnnotation(Valid.class);
                if (fieldAnnotation != null || methodAnnotation != null) {
                    if (STRING.equals(method.getReturnType().getName())) {
                        String value = (String) method.invoke(t);
                        if (null == value || value.trim().isEmpty()) {
                            hasError.getAndIncrement();
                            String info = String.format(Locale.ROOT, errorInfo, filedName);
                            stringBuilder.append(info);
                        }
                    } else if (INT.equals(method.getReturnType().getName())) {
                        int value = (int) method.invoke(t);
                        if (value == 0) {
                            hasError.getAndIncrement();
                            String info = String.format(Locale.ROOT, errorInfo, filedName);
                            stringBuilder.append(info);
                        }
                    } else if (DOUBLE.equals(method.getReturnType().getName())) {
                        double value = (double) method.invoke(t);
                        if (value == 0) {
                            hasError.getAndIncrement();
                            String info = String.format(Locale.ROOT, errorInfo, filedName);
                            stringBuilder.append(info);
                        }
                    }
                }

            } catch (Exception exception) {
            }
        });
        String exceptionInfo = stringBuilder.toString();
        if (hasError.get() > 0) {
            throw new ValidationException(exceptionInfo);
        }
    }
}
