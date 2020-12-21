package com.data.big.mapper;

import com.data.big.model.AuthOperationLog;

import java.util.List;

public interface AuthOperationLogMapper {
    List<AuthOperationLog> selectOperationLogList();
}
