package com.data.big.util;

import com.data.big.gw.GwaqscJxglServicePortType;
import com.data.big.service.Service;
import com.data.big.service.ServiceFZ;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThread extends Thread {
    private Object service;

    public MyThread(Object service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        if (service instanceof Service) {
            log.info("---------------开始获取公务连接信息---------------------");
            GwaqscJxglServicePortType portType = null;
            try {
                FZMap.clientTokenLock.readLock().lock();
                portType = (GwaqscJxglServicePortType) FZMap.clientToken.get("GW");

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                FZMap.clientTokenLock.readLock().unlock();
            }
            if (portType == null) {
                ((Service) service).getportType();
            }

            log.info("---------------结束获取公务连接信息---------------------");
        } else if (service instanceof ServiceFZ) {
            ((ServiceFZ) service).getClient();
            ((ServiceFZ) service).login();
        }
    }
}
