package com.data.big.service;


import com.data.big.model.AuthAccountLog;

import java.util.List;

/**
 * @author tomsun28
 * @date 9:30 2018/4/22
 */
public interface AccountLogService {

    /**
     * description TODO
     *
     * @return java.util.List<com.usthe.bootshiro.domain.bo.AuthAccountLog>
     */
    List<AuthAccountLog> getAccountLogList();
}
