package com.data.big.task;

import java.util.Date;

import com.data.big.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class KeepTask implements SchedulingConfigurer {

    private Integer saKeepAlivePeriod = 30;
    @Autowired
    private Service service;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
/*
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    service.executeTask("", "");
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    return new Date(new Date().getTime() + saKeepAlivePeriod * 1000);
                }
        );*/
    }

    public Integer getSaKeepAlivePeriod() {
        return saKeepAlivePeriod;
    }

    public void setSaKeepAlivePeriod(Integer saKeepAlivePeriod) {
        this.saKeepAlivePeriod = saKeepAlivePeriod;
    }

}
