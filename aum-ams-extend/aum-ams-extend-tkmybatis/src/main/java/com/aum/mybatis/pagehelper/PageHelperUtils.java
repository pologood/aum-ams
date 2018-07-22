package com.aum.mybatis.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tk.mybatis.mapper.entity.Example;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author xiayx
 */
public abstract class PageHelperUtils {

    public static <T> Page<T> startPage(Pageable pageable) {
        Page<T> page = PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        if (pageable.getSort() != null) page.setOrderBy(toOrderBy(pageable.getSort()));
        return page;
    }

    public static PageRowBounds mapToRowBounds(Pageable pageable) {
        return new PageRowBounds((int) pageable.getOffset(), pageable.getPageSize());
    }

    public static void orderBy(Example example, Pageable pageable) {
        orderBy(example, pageable.getSort());
    }

    public static void orderBy(Example example, Sort sort) {
        if (sort == null) return;
        String orderBy = toOrderBy(sort);
        example.setOrderByClause(orderBy);
    }

    private static String toOrderBy(Sort sort) {
        return StreamSupport.stream(sort.spliterator(), false)
                    .map(order -> order.getProperty() + " " + order.getDirection().name())
                    .collect(Collectors.joining(","));
    }

}
