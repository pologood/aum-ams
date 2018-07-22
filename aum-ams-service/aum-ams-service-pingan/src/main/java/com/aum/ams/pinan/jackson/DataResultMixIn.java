package com.aum.ams.pinan.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * mix in for {@link com.github.peacetrue.result.DataResult}
 *
 * @author xiayx
 */
public interface DataResultMixIn<T> {

    @JsonProperty("TxnReturnCode")
    String getCode();

    @JsonProperty("TxnReturnMsg")
    String getMessage();

    @JsonUnwrapped
    T getData();
}
