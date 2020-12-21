package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.AuthRoleResource;

public interface AuthRoleResourceMapper extends BaseMapper<AuthRoleResource> {
    int insert(AuthRoleResource record);

    int insertSelective(AuthRoleResource record);

    AuthRoleResource selectByPrimaryId(Integer id);

    int deleteByUniqueKey(Integer roleId, Integer resourceId);
}