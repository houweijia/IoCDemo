package com.jiehun.veigar.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: houwj
 * @date: 2019/3/21
 */
@Target({ElementType.FIELD})//该注解作用在属性之上
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    int value();
}
