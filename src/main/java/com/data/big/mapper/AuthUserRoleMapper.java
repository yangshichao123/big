package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.AuthUserRole;

public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {
    int insert(AuthUserRole record);

    int insertSelective(AuthUserRole record);

    AuthUserRole selectByPrimaryId(Integer id);

    int deleteByUniqueKey(AuthUserRole authUserRole);
}