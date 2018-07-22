package com.aum.tutorial;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.Mapping;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Human<T> {
        private String id;
        private String name;
        private T child;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Son {
        private String id;
        private String name;
    }

    private Mapper mapper = new DozerBeanMapper(Arrays.asList("dozerBeanMapping.xml"));
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void convert() throws Exception {
        A a = new A();
        a.setNameA("name");
        B b = mapper.map(a, B.class);
        Assert.assertEquals(a.getNameA(), b.getNameB());
    }

    @Test
    public void mapToBean() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "id");
        map.put("name", "name");
        map.put("childId", "childId");
        map.put("childName", "childName");
        map.put("self", map);
        Human human = mapper.map(map, Human.class);
        System.out.println(objectMapper.writeValueAsString(human));
    }

    @Test
    public void beanToMap() throws Exception {
        Human human = new Human<>("id", "name", new Son("childId", "childName"));
        HashMap map = mapper.map(human, HashMap.class, "mapCommonId");
        System.out.println(map);
        Human human1 = mapper.map(map, Human.class, "mapCommonId");
        System.out.println(objectMapper.writeValueAsString(human1));
    }
}
