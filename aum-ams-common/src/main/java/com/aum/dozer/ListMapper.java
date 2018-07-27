package com.aum.dozer;

import org.dozer.Mapper;
import org.dozer.MappingException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 支持转换集合
 *
 * @author xiayx
 */
public interface ListMapper extends Mapper {

    default <T> List<T> map(List<?> source, Class<T> destinationClass) throws MappingException {
        return source.stream().map(object -> this.map(object, destinationClass)).collect(Collectors.toList());
    }

    default void map(List<?> source, List<?> destination) throws MappingException {
        final int min = Math.min(source.size(), destination.size());
        for (int i = 0; i < min; i++) {
            this.map(source.get(i), destination.get(i));
        }
    }

    default <T> List<T> map(List<?> source, Class<T> destinationClass, String mapId) throws MappingException {
        return source.stream().map(object -> this.map(object, destinationClass, mapId)).collect(Collectors.toList());
    }

    default void map(List<?> source, List<?> destination, String mapId) throws MappingException {
        final int min = Math.min(source.size(), destination.size());
        for (int i = 0; i < min; i++) {
            this.map(source.get(i), destination.get(i), mapId);
        }
    }

}
