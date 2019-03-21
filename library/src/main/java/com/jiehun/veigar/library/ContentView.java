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

@Target({ElementType.TYPE})//Target该注解作用于xxx之上  TYPE 作用于类、接口、枚举   FIELD 作用于属性、枚举上  METHOD作用在方法上 ANNOTATION_TYPE可以作用在注解之上 元注解
@Retention(RetentionPolicy.RUNTIME)//CLASS 预编译操作 预处理 在CLASS文件里存在 在运行时会丢失  SOURCE 源码级 做一些检查性的操作 在运行时会丢失 在编译时也会丢失 RUNTIME jvm通过反射获取到该注解
public @interface ContentView {
    int value();
}
