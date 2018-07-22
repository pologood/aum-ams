package com.aum.ams;

import com.aum.ams.pinan.AsmUtils;
import com.aum.ams.result.DataResultException;
import com.aum.ams.result.ResultException;
import com.github.peacetrue.result.Result;
import com.github.peacetrue.result.ResultImpl;
import com.pabank.sdk.PABankSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 平安服务工具类
 *
 * @author xiayx
 */
public class AumAmsServicePingAnUtils extends AumAmsServiceApiUtils {

    protected static Logger logger = LoggerFactory.getLogger(AumAmsServicePingAnUtils.class);

    /** 接口码：ClientSign */
    public static final String
            API_CLIENT_SIGN = "ClientSign";

    /** 操作结果 */
    public static final Result
            RESULT_PING_AN_SUCCESS = new ResultImpl("000000", "操作成功"),
            RESULT_PING_AN_FAIL = new ResultImpl("fail", "操作失败");

    /** 初始化平安配置文件 */
    public static void initConfig(String path) throws IOException {
        logger.info("修改平安类库字节码以支持读取类路径配置文件");
        AsmUtils.loadPingAnChanged("com.pabank.sdk.common.Config");
        AsmUtils.loadPingAnChanged("com.pabank.container.protocol.config.ProtocolManage");
        AsmUtils.loadPingAnChanged("com.pabank.sdk.utils.RSASign");
        AsmUtils.loadPingAnChanged("com.dcfs.esb.ftp.client.https.FtpClientConfig");
        AsmUtils.loadPingAnChanged("com.pabank.container.protocol.http.client.HttpSchemeSimpleFactory");
        logger.info("平安类库字节码修改并加载完成");
        // 初始化配置
        PABankSDK.init(path);
        // a.验证开发者
        PABankSDK.getInstance().approveDev();
    }


    /** 检查返回结果是否正确 */
    public static void checkResult(Result result) throws ResultException {
        if (result.getCode().equals(RESULT_PING_AN_SUCCESS.getCode())) return;
        //TODO 异常转码，将平安银行的异常码转换为黄金管家的异常码
        throw new DataResultException(RESULT_FAIL, result);
    }


}
