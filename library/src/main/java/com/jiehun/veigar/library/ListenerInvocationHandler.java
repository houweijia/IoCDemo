package com.jiehun.veigar.library;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @description:
 * @author: houwj
 * @date: 2019/3/21
 */
public class ListenerInvocationHandler implements InvocationHandler {
    //我需要拦截MainActivity中的某些方法
    private Object target;
    private HashMap<String,Method> methodMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if(target!=null){
            String methodName = method.getName();//假如是onClick
            //重新复制，并且拦截了 onClick方法
            method = methodMap.get(methodName);
            if(method!=null){
                return method.invoke(target,objects);
            }
        }
        return null;
    }

    /**
     * 拦截的添加
     * @param methodName 本应该执行的方法 onClick 拦截
     * @param method 自定义的方法 show() onClick()
     */
    public void addMethod(String methodName,Method method){
        methodMap.put(methodName,method);
    }
}
