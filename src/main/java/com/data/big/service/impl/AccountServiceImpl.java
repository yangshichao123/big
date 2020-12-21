package com.data.big.service.impl;

import com.data.big.mapper.AuthUserMapper;
import com.data.big.model.AuthUser;
import com.data.big.service.AccountService;
import com.data.big.service.UserService;
import com.data.big.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author tomsun28
 * @date 22:04 2018/3/7
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthUserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public Account loadAccount(String appId) throws DataAccessException {
        AuthUser user = userMapper.selectByUniqueKey(appId);
        return user != null ? new Account(user.getUsername(),user.getPassword(),user.getSalt()) : null;
    }

    @Override
    public boolean isAccountExistByUid(String uid) {
        AuthUser user = userMapper.selectByPrimaryKey(uid);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(AuthUser account) throws DataAccessException {

        // 给新用户授权访客角色
        userService.authorityUserRole(account.getUid(),103);

        return userMapper.insertSelective(account) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public String loadAccountRole(String appId) throws DataAccessException {

        return userMapper.selectUserRoles(appId);
    }


}
