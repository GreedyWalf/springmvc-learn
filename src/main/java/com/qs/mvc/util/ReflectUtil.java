package com.qs.mvc.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtil {

    public static <T> Class<T> getGenericParamClass(Class<?> clazz) {
        ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> typeArgument = (Class<T>) actualTypeArguments[0];
        return typeArgument;
    }
}
