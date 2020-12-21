package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.AuthUser;

import java.util.List;

public interface AuthUserMapper extends BaseMapper<AuthUser> {
    int insert(AuthUser record);

    int insertSelective(AuthUser record);

    AuthUser selectByPrimaryId(String uid);

    AuthUser selectByUniqueKey(String appId);

    String selectUserRoles(String appId);

    List<AuthUser> selectUserList();

    List<AuthUser> selectUserListByRoleId(Integer roleId);

    List<AuthUser> selectUserListExtendByRoleId(Integer roleId);
}