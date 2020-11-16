package com.data.big.util;

import org.apache.cxf.endpoint.Client;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FZMap {

    /**
     * 登录用户信息 key 为会话编号cuId
     *
     * @return
     */
    public static final HashMap<String, String> loginToken = new HashMap<>();
    // 互斥锁
    public static ReentrantReadWriteLock loginTokenLock = new ReentrantReadWriteLock(); /**
     * 登录用户信息 key 为会话编号cuId
     *
     * @return
     */
    public static final HashMap<String,Object> clientToken = new HashMap<>();
    // 互斥锁
    public static ReentrantReadWriteLock clientTokenLock = new ReentrantReadWriteLock();

}
