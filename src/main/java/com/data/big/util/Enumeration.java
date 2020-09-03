package com.data.big.util;

/**
 * 命令请求结果
 *
 * @author yangshichao
 */
public class Enumeration {
    /**
     * 用户登陆类
     */
    public static int CRCC_CMD_RESULT_FAIL = -1; // 失败
    public static int CRCC_CMD_RESULT_SUCCESS = 0; // 成功
    public static int CRCC_CMD_RESULT_USER_ACCOUNT_FAILURE = 1; // 用户不存在
    public static int CRCC_CMD_RESULT_USER_PASSWORD_FAILURE = 2; // 密码不正确
    public static int CRCC_CMD_RESULT_USER_VALIDTIME = 200; // 不在有效的活动时间范围内


    /**
     * 日志类
     * 用户登陆
     */
    public static final String USER_OPT_LOGIN = "1";

    /**
     * 系统参数设置
     */
    public static final String SYSTEM_OPT = "2";
    /**
     * 用户业务操作
     */
    public static final String USER_BUSINESS_OPT = "3";

    /**
     * 用户业务操作-请求历史视频
     */
    public static final String USER_BUSINESS_OPT_REQUEST_HIS_VIDEO = "4";

    /**
     * 用户业务操作-请求历史视频下载
     */
    public static final String USER_BUSINESS_OPT_REQUEST_DOWNLOAD_HIS_VIDEO = "5";

    /**
     * 实时媒体播放
     */
    public static final String REAL_TIME_PLAYOPEN = "6";

}
