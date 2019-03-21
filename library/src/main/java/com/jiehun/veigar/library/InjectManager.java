package com.jiehun.veigar.library;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: houwj
 * @date: 2019/3/21
 */
public class InjectManager {

    public static void inject(Activity activity) {
        //布局注入
        injectLayout(activity);

        //控件注入
        injectView(activity);

        //事件注入
        injectEvent(activity);
    }

    private static void injectLayout(Activity activity) {
        //获取类
        Class<? extends Activity> clazz = activity.getClass();
        //获取类的注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        //获取注解的值
        if(contentView!=null){
            //获取布局的id
            int layoutId = contentView.value();
            //第一种方式
            //activity.setContentView(layoutId);

            //第二种方式 通过反射
            try {
                Method setContentView = clazz.getMethod("setContentView",int.class);
                setContentView.invoke(activity,layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    private static void injectView(Activity activity) {
        //获取类
        Class<? extends Activity> clazz = activity.getClass();

        //获取类所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if(injectView!=null){//并不是所有属性都有注解
                //获取注解的值
                int viewId = injectView.value();
                try {
                    //获取findViewById方法
                    Method findViewById = clazz.getMethod("findViewById", int.class);
                    //第一种方法
                    //Object view = activity.findViewById(viewId);
                    Object view = findViewById.invoke(activity, viewId);

                    //属性的值赋给控件，在当前activity
                    //当属为private 无法赋值 需要设置setAccessible为true
                    field.setAccessible(true);
                    field.set(activity,view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private static void injectEvent(Activity activity) {
        //获取类
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method:methods) {
            //获取每个方法的注解 可能有多个注解
            Annotation[] annotations = method.getAnnotations();

            for (Annotation annotation:annotations) {
                //获取OnClick注解上的注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if(annotationType!=null){
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    //获取三个重要成员
                    String listenerSetter = eventBase.listenerSetter();
                    Class<?> listenerType = eventBase.listenerType();
                    String callBackListener = eventBase.callBackListener();

                    try {
                        //通过annotationType获取onClick注解的value值，拿到R.id.xxx
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) valueMethod.invoke(annotation);

                        //拦截
                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                        handler.addMethod(callBackListener,method);

                        //代理方式
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),new Class[]{listenerType},handler);

                        for (int viewId:viewIds) {
                            //获取当前view
                            View view = activity.findViewById(viewId);
                            Method setter = view.getClass().getMethod(listenerSetter,listenerType);
                            setter.invoke(view,listener);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
