package com.aum.ams.pinan.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * mix in for {@link org.springframework.data.domain.Page}
 *
 * @author xiayx
 */
public interface PingAnPageMixIn<T> {

    @JsonProperty("TotalNum")
    String getTotalElements();

    @JsonProperty("ResultNum")
    String getNumberOfElements();

    @JsonProperty("Content")
    T getContent();
}
