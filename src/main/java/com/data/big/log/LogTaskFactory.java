package com.data.big.log;

import com.data.big.mapper.LogRestMapper;
import com.data.big.model.LogRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

/**
 *  日志操作任务工厂
 * @author tomsun28
 * @date 9:44 2018/4/22
 */
@Component
public class LogTaskFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogTaskFactory.class);
    @Autowired
    private LogRestMapper logRestMapper;

    private LogTaskFactory() {

    }

    public  TimerTask crezteLog(String logName, String type, String ip, String paramin, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LogRest accountLog = LogFactory.createAccountLog(logName, type, ip, paramin, message);
                    logRestMapper.insertSelective(accountLog);
                } catch (Exception e) {
                    LOGGER.error("写入日志异常", e.getCause().getMessage());
                }
            }
        };
    }
/*

    public static TimerTask exitLog(String userId, String ip, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LogRest accountLog = LogFactory.createAccountLog(userId, "用户退出日志", ip, succeed, message);
                    logRestMapper.insertSelective(accountLog);
                } catch (Exception e) {
                    LOGGER.error("写入用户退出日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask registerLog(String userId, String ip, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LogRest accountLog = LogFactory.createAccountLog(userId, "用户注册日志", ip, succeed, message);
                    logRestMapper.insertSelective(accountLog);
                } catch (Exception e) {
                    LOGGER.error("写入用户注册日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask bussinssLog(String userId, String api, String method, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LogRest operationLog = LogFactory.createOperationLog(userId, "业务操作日志", api, method, succeed, message);
                    logRestMapper.insertSelective(operationLog);
                } catch (Exception e) {
                    LOGGER.error("写入业务操作日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask exceptionLog(String userId, String api, String method, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LogRest exceptionLog = LogFactory.createOperationLog(userId, "业务异常日志", api, method, succeed, message);
                    logRestMapper.insertSelective(exceptionLog);
                } catch (Exception e) {
                    LOGGER.error("写入业务异常日志异常", e.getCause().getMessage());
                }
            }
        };
    }
*/


}
