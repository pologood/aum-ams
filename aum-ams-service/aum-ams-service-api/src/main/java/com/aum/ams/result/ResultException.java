package com.aum.ams.result;

import com.github.peacetrue.result.Result;

/**
 * 结果异常，
 * 不直接使用{@link com.github.peacetrue.result.exception.ResultException}，
 * 兼容dubbo处理
 *
 * @author xiayx
 * @see DataResultException
 */
public class ResultException extends com.github.peacetrue.result.exception.ResultException {

    public ResultException(String code, String message) {
        super(code, message);
    }

    public ResultException(Result result) {
        super(result);
    }
}
