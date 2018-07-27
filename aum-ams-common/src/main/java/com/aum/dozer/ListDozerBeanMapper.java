package com.aum.dozer;

import org.dozer.DozerBeanMapper;

import java.util.List;

/**
 * 支持转换集合
 *
 * @author xiayx
 */
public class ListDozerBeanMapper extends DozerBeanMapper implements ListMapper {

    public ListDozerBeanMapper() {
    }

    public ListDozerBeanMapper(List<String> mappingFiles) {
        super(mappingFiles);
    }

}
