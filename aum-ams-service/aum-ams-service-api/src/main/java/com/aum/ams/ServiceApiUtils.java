package com.aum.ams;

import com.aum.ams.result.DataResultException;
import com.aum.ams.result.ResultException;
import com.github.peacetrue.result.Result;
import com.github.peacetrue.result.ResultImpl;

/**
 * 服务接口工具类.
 * <p>
 * 定义服务实现前缀以标识服务的类型，一个服务可能会有多种实现，目前已知的包括：门面实现、本地实现、平安实现。
 * 定义服务引用前缀以标识服务的类型，每种实现可能会有不同用途，目前已知的包括：作为第三方服务、作为消息队列服务。
 * dubbo的服务分组遵循此规则。
 *
 * @author xiayx
 */
public abstract class ServiceApiUtils {

    /** 服务实现前缀：门面、本地、平安 */
    public static final String
            IMPL_PREFIX_FACADE = "facade",
            IMPL_PREFIX_LOCAL = "local",
            IMPL_PREFIX_PINGAN = "pingAn";
    /** 服务引用前缀：第三方、消息队列 */
    public static final String
            REFER_PREFIX_THIRD = "third",
            REFER_PREFIX_AMQP = "amqp";

    /** 操作结果 */
    public static final Result
            RESULT_SUCCESS = new ResultImpl("success", "操作成功"),
            RESULT_FAIL = new ResultImpl("fail", "操作失败");

    /** 获取信息. see {@link java.util.function.Supplier} */
    public interface ThrowableSupplier<T> {
        T get() throws Exception;
    }

    /** 执行一段抛出异常的代码，将异常转换为运行时异常 */
    public static <T> T uncheckedInvoke(ThrowableSupplier<T> supplier) {
        return uncheckedInvoke(supplier, RESULT_FAIL);
    }

    /** 执行一段抛出异常的代码，将异常转换为运行时异常 */
    public static <T> T uncheckedInvoke(ThrowableSupplier<T> supplier, Result result) {
        try {
            return supplier.get();
        } catch (Exception e) {
            if (e instanceof ResultException) throw (ResultException) e;
            else throw new DataResultException(result, e.getMessage());
        }
    }

}
