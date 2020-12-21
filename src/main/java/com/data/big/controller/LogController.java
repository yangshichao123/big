package com.data.big.controller;

import com.data.big.model.AuthAccountLog;
import com.data.big.model.AuthOperationLog;
import com.data.big.service.AccountLogService;
import com.data.big.service.OperationLogService;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tomsun28
 * @date 12:20 2018/4/22
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseAction {

    @Autowired
    AccountLogService accountLogService;

    @Autowired
    OperationLogService operationLogService;

    @SuppressWarnings("unchecked")
   // @ApiOperation(value = "获取日志记录", httpMethod = "GET")
    @RequestMapping("/accountLog/{currentPage}/{pageSize}")
    public Message getAccountLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize ) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthAccountLog> accountLogs = accountLogService.getAccountLogList();
        PageInfo pageInfo = new PageInfo(accountLogs);
        return new Message().ok("success",pageInfo);
    }

    @SuppressWarnings("unchecked")
  //  @ApiOperation(value = "获取用户操作api日志列表", httpMethod = "GET")
    @RequestMapping("/operationLog/{currentPage}/{pageSize}")
    public Message getOperationLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthOperationLog> authOperationLogs = operationLogService.getOperationList();
        PageInfo pageInfo = new PageInfo(authOperationLogs);
        return new Message().ok( "success", pageInfo);
    }
}
