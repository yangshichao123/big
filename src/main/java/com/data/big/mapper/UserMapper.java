package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.User;

public interface UserMapper extends BaseMapper<User> {
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryId(String id);
}