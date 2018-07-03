package com.aum.mybatis.tk;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;

/**
 * tests for tk.mybatis and pagehelper
 *
 * @author xiayx
 */
@ContextConfiguration(classes = PageHelperAutoConfiguration.class)
public abstract class TkPageHelperMybatisTest extends TkMybatisTest {}