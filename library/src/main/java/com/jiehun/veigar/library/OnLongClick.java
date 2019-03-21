package com.jiehun.veigar.library;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: houwj
 * @date: 2019/3/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnLongClickListener",listenerType = View.OnLongClickListener.class,callBackListener = "onLongClick")
public @interface OnLongClick {
    int[] value();
}
