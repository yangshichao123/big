package com.data.big.service;


import com.data.big.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ServiceUser {

    /**
     * 用户登陆
     *
     * @return
     */
    Map<String,Object> login(HttpServletRequest request, User user);


    /**
     * 添加用户
     *
     * @return
     */
    Map<String,Object> addUser(User user);

    /**
     * 删除用户
     *
     * @return
     */
    Map<String,Object> deleteUser(User user);

    /**
     * 修改用户
     *
     * @return
     */
    Map<String,Object> updateUser(User user);

    /**
     * 查询用户
     *
     * @return
     */
    Map<String,Object> getUser(User user);

    /**
     * 添加角色
     *
     * @return
     */
    Map<String,Object> addRole(Role role);

    /**
     * 删除角色
     *
     * @return
     */
    Map<String,Object> deleteRole(Role role);

    /**
     * 修改角色
     *
     * @return
     */
    Map<String,Object> updateRole(Role role);

    /**
     * 查询角色
     *
     * @return
     */
    Map<String,Object> getRole(Role role);

    /**
     * 删除模块
     *
     * @return
     */
    Map<String,Object> deleteModular(Modular modular);

    /**
     * 添加模块
     *
     * @return
     */
    Map<String,Object> addModular(Modular modular);

    /**
     * 查询模块
     *
     * @return
     */
    Map<String,Object> getModular(Modular modular);

    /**
     * 查询token是否过期  过期的话 删除缓存 数据库置为过期
     */
    void checkToken();

    /**
     * 检测token值是否合法
     *
     * @param token
     * @param userId
     * @return
     */
    Map<String,String> judgeTokenIsEqual(String token, String userId);

    /**
     * 获取token
     *
     * @param user
     * @return
     */
    Map<String,Object> getToken(User user);
}
