package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.AuthRole;

import java.util.List;

public interface AuthRoleMapper extends BaseMapper<AuthRole> {
    int insert(AuthRole record);

    int insertSelective(AuthRole record);

    AuthRole selectByPrimaryId(Integer id);

    List<AuthRole> selectRoles();
}