package com.data.big.util;


import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 该类提供各种功能HashMap
 *
 * @author yangshichao
 */
public class HashMapHelper {


    /**
     * 登录用户信息 key 为用户id
     *
     * @return
     */
    public static final HashMap<String,String> loginUser = new HashMap<>();
    /**
     * web页面登陆用户 key 为sessionid
     */
    public static final HashMap<String,String> loginUserSession = new HashMap<>();
    // 互斥锁
    public static ReentrantReadWriteLock userLongin = new ReentrantReadWriteLock();

}
