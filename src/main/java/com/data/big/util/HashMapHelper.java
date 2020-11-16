package com.data.big.util;


import com.data.big.model.User;

import javax.servlet.http.HttpServletRequest;
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
    public static final HashMap<String,User> loginUser = new HashMap<>();

    // 互斥锁
    public static ReentrantReadWriteLock loginUserLock = new ReentrantReadWriteLock();

}
