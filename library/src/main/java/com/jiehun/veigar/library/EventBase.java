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
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    //时间3个重要成员

    //1 setOnClickListener 方法名
    String listenerSetter();

    //2 监听的对象 View.OnClickListener
    Class<?> listenerType();

    //3 回调方法 onClick(View v)
    String callBackListener();



}