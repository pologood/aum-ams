package com.aum.ams.pinan;

/**
 * 平安分页数据 see {@link org.springframework.data.domain.Page}
 *
 * @author xiayx
 */
public class PingAnPage<T> {

    private Integer totalElements;
    private Integer numberOfElements;
    private T content;

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
