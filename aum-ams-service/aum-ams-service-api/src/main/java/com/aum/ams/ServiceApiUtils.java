package com.aum.ams;

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

}
