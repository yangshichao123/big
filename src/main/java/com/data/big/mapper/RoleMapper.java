package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Role;

public interface RoleMapper extends BaseMapper<Role> {
    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryId(Integer id);
}