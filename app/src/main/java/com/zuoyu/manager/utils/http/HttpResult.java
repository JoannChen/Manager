package com.zuoyu.manager.utils.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <pre>
 * Function：网络请求返回结果
 *
 * Created by JoannChen on 2017/3/7 14:46
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public abstract class HttpResult<T> {
    public int type;

    public Class<T> obj;

    public HttpResult() {

        Class clazz = getClass();

        Type t = clazz.getGenericSuperclass();
        Type[] args = ((ParameterizedType) t).getActualTypeArguments();


        // 是否包含集合类型
        if (args[0].toString().contains(List.class.getName())) {
            Type[] args2 = ((ParameterizedType) args[0]).getActualTypeArguments();
            obj = (Class<T>) args2[0];
            type = HttpUtil.ARRAY_TYPE;
        } else {
            obj = (Class<T>) args[0];
            type = HttpUtil.OBJECT_TYPE;
        }

    }

    public abstract void onLoadCatch(String json);

    public abstract void onSuccess(T result);

    public abstract void onFailed(int errCord, String errMsg);

    public Class<T> getObj() {
        return obj;
    }

    public int getType() {
        return type;
    }

}
