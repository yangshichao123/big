package com.data.big.task;

import com.data.big.netty.nettyThread;
import com.data.big.service.Service;
import com.data.big.service.ServiceNetty;
import com.data.big.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class taskThread {
    static ApplicationContext applicationContext; // Spring应用上下文
    private Service service;
    private ServiceNetty serviceNetty;
    // 日志记录器
    private static final Logger logger = LogManager.getLogger(nettyThread.class);


    public taskThread(ApplicationContext applicationContext) {
        taskThread.applicationContext = applicationContext;
        service= (Service)applicationContext.getBean("Service");
        serviceNetty= (ServiceNetty)applicationContext.getBean("ServiceNetty");
        this.execuid();
    }

    private void execuid() {

        String nettyStartOrNot = Properties.getNettyStartOrNot();
        if("false".equals(nettyStartOrNot)){
            return;
        }
        new Thread(() -> {
            while (true) {
                try {
                    service.addGWTask();
                    serviceNetty.addFZTask();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

                try {
                    long l=5*60*1000;
                    Thread.sleep(l);
                    logger.info("---------------执行添加视频任务---------------------");
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();

    }

}
