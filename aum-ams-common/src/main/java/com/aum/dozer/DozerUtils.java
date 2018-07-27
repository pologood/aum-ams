package com.aum.dozer;

import org.dozer.Mapper;
import org.dozer.MappingException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * dozer工具类
 *
 * @author xiayx
 */
public abstract class DozerUtils {

    private static Mapper mapper;

    public static <T> List<T> map(List<?> source, Class<T> destinationClass) throws MappingException {
        return source.stream().map(object -> mapper.map(object, destinationClass)).collect(Collectors.toList());
    }

    public static void map(List<?> source, List<?> destination) throws MappingException {
        final int min = Math.min(source.size(), destination.size());
        for (int i = 0; i < min; i++) {
            mapper.map(source.get(i), destination.get(i));
        }
    }

    public static <T> List<T> map(List<?> source, Class<T> destinationClass, String mapId) throws MappingException {
        return source.stream().map(object -> mapper.map(object, destinationClass, mapId)).collect(Collectors.toList());
    }

    public static void map(List<?> source, List<?> destination, String mapId) throws MappingException {
        final int min = Math.min(source.size(), destination.size());
        for (int i = 0; i < min; i++) {
            mapper.map(source.get(i), destination.get(i), mapId);
        }

    }

}
