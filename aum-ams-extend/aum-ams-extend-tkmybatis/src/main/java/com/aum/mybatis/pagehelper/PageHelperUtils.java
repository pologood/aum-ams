package com.aum.mybatis.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
import org.springframework.data.domain.Pageable;
import tk.mybatis.mapper.entity.Example;

/**
 * @author xiayx
 */
public abstract class PageHelperUtils {

    public static <T> Page<T> startPage(Pageable pageable) {
        Page<T> page = PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        if (pageable.getSort() != null) page.setOrderBy(pageable.getSort().toString());
        return page;
    }

    public static PageRowBounds mapToRowBounds(Pageable pageable) {
        return new PageRowBounds(pageable.getOffset(), pageable.getPageSize());
    }

    public static void orderBy(Example example, Pageable pageable) {
        if (pageable.getSort() == null) return;
        example.orderBy(pageable.getSort().toString());
    }

}
