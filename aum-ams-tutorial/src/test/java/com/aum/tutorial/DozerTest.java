package com.aum.tutorial;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.Mapping;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiayx
 */
public class DozerTest {

    public static class A {
        @Mapping("nameB")
        private String nameA;

        public String getNameA() {
            return nameA;
        }

        public void setNameA(String nameA) {
            this.nameA = nameA;
        }
    }

    public static class B {
        private String nameB;

        public String getNameB() {
            return nameB;
        }

        public void setNameB(String nameB) {
            this.nameB = nameB;
        }
    }

    @Test
    public void convert() throws Exception {
        Mapper mapper = new DozerBeanMapper();
        A a = new A();
        a.setNameA("name");
        B b = mapper.map(a, B.class);
        Assert.assertEquals(a.getNameA(), b.getNameB());
    }
}
