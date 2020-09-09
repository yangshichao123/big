package com.data.big.netty;

import com.data.big.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class nettyThread {
    static ApplicationContext applicationContext; // Spring应用上下文
    // 日志记录器
    private static final Logger logger = LogManager.getLogger(nettyThread.class);


    public nettyThread(ApplicationContext applicationContext) {
        nettyThread.applicationContext = applicationContext;
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
                    new BootNettyClient().connect();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();

    }

}
