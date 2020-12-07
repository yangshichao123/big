package com.data.big.log;

import com.data.big.model.LogRest;

import java.util.Date;

/**
 *   日志对象工厂
 * @author tomsun28
 * @date 9:50 2018/4/22
 */
public class LogFactory {

    private LogFactory() {

    }

    /**
     * 创建日志
     * @param logName 方法名
     * @param type 类型
     * @param ip  IP
     * @param paramin  参数
     * @param message  内容
     * @return
     */
    public static LogRest createAccountLog(String logName, String type, String ip, String paramin, String message) {
        LogRest accountLog = new LogRest();
        accountLog.setFunname(logName);
        accountLog.setType(type);
        accountLog.setIp(ip);
        accountLog.setParamin(paramin);
        accountLog.setRedata(message);
        accountLog.setLrsj(new Date());
        return accountLog;
    }
/*
    public static LogRest createOperationLog(String userId,String logName,String api, String method, Short succeed, String message) {
        AuthOperationLog operationLog = new AuthOperationLog();
        operationLog.setUserId(userId);
        operationLog.setLogName(logName);
        operationLog.setApi(api);
        operationLog.setMethod(method);
        operationLog.setSucceed(succeed);
        operationLog.setMessage(message);
        operationLog.setCreateTime(new Date());
        return operationLog;
    }*/
}
