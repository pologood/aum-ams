package com.aum.ams.result;

import com.github.peacetrue.result.DataResult;
import com.github.peacetrue.result.Result;

/**
 * 数据结果异常，
 * 不直接使用{@link com.github.peacetrue.result.exception.ResultException}，
 * 兼容dubbo处理
 *
 * @author xiayx
 * @see ResultException
 */
public class DataResultException extends com.github.peacetrue.result.exception.DataResultException {

    public DataResultException(String code, String message, Object data) {
        super(code, message, data);
    }

    public DataResultException(Result result, Object data) {
        super(result, data);
    }

    public DataResultException(DataResult<?> result) {
        super(result);
    }
}
