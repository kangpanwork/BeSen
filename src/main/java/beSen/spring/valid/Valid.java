package beSen.spring.valid;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 康盼Java开发工程师
 */
@Target({ METHOD, FIELD})
@Retention(RUNTIME)
public @interface Valid {
}
