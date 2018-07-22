package com.aum.vo;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * VO转换工具类
 *
 * @author xiayx
 */
public abstract class VoConvertUtils {

    /** 简单的Bean集合转换，源对象和目标对象属性名和类型都必须相同 */
    public static <T> List<T> convert(List<?> source, Class<T> targetClass) {
        return source.stream().map(o -> convert(o, targetClass)).collect(Collectors.toList());
    }

    /** 简单的Bean集合元素替换，源对象和目标对象属性名和类型都必须相同 */
    @SuppressWarnings("unchecked")
    public static <S, T extends S> List<T> replace(List<S> source, Class<T> targetClass) {
        for (int i = 0; i < source.size(); i++) {
            source.set(i, convert(source.get(i), targetClass));
        }
        return (List<T>) source;
    }

    /** 简单的Bean转换，源对象和目标对象属性名和类型都必须相同 */
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) return null;
        T target = BeanUtils.instantiate(targetClass);
        BeanUtils.copyProperties(source, target);
        return target;
    }


}
