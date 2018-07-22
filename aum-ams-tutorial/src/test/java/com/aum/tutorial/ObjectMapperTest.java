package com.aum.tutorial;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.peacetrue.result.DataResultImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiayx
 */
public class ObjectMapperTest {

    public static class Parent {
        public int age;
        @JsonUnwrapped
        public Name name;
    }

    public static class Name {
        public String first, last;
    }

    public static class Generic<T> {
        @JsonUnwrapped
        private T data;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void JsonUnwrapped() throws Exception {
        Parent parent = new Parent();
        parent.age = 18;
        parent.name = new Name();
        parent.name.first = "first";
        parent.name.last = "last";

        String string = objectMapper.writeValueAsString(parent);
        System.out.println(string);

        Parent backParent = objectMapper.readValue(string, Parent.class);
        System.out.println(objectMapper.writeValueAsString(backParent));

        Generic<Name> generic = new Generic<>();
        generic.data = parent.name;
        string = objectMapper.writeValueAsString(generic);
        System.out.println(string);

        generic = objectMapper.readValue(string, new TypeReference<Generic<Name>>() {});
        System.out.println(objectMapper.writeValueAsString(generic));
    }

    public static abstract class DataResultMixIn<T> {
        @JsonUnwrapped
        public T data;
    }

    @Test
    public void mixIn() throws Exception {
        objectMapper.addMixIn(DataResultImpl.class, DataResultMixIn.class);
        DataResultImpl<Name> dataResult = new DataResultImpl<>("success", "操作成功", new Name());
        dataResult.getData().first = "first";
        dataResult.getData().last = "last";
        String string = objectMapper.writeValueAsString(dataResult);
        System.out.println(string);
        dataResult = objectMapper.readValue(string, new TypeReference<DataResultImpl<Name>>() {});
        System.out.println(objectMapper.writeValueAsString(dataResult));
    }


    @AllArgsConstructor
    public static class Source {
        @JsonProperty("targetName")
        private String sourceName;
        @JsonProperty("name")
        private String sourceName2;
        private String sourceName3;
    }

    @Data
    public static class Dist {
        private String targetName;
        @JsonProperty("name")
        private String targetName2;
        @JsonProperty("sourceName3")
        private String targetName3;
    }

    @Test
    public void convertValue() throws Exception {
        Source source = new Source("name", "name2", "name3");
        Dist dist = objectMapper.convertValue(source, Dist.class);
        Assert.assertEquals(source.sourceName, dist.targetName);
        Assert.assertEquals(source.sourceName2, dist.targetName2);
        Assert.assertNotEquals(source.sourceName3, dist.targetName3);
    }
}
