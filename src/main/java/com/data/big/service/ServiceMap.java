package com.data.big.service;

import com.data.big.model.Ipcseelocation;
import com.data.big.model.Screenresolving;
import com.data.big.vo.Message;

public interface ServiceMap {
    /**
     * 添加摄像机位置
     *
     * @param ipcseelocation
     * @return
     */
    Message addIpcseelocation(Ipcseelocation ipcseelocation);

    /**
     * 修改摄像机位置
     *
     * @param ipcseelocation
     * @return
     */
    Message updateIpcseelocation(Ipcseelocation ipcseelocation);

    /**
     * 删除摄像机位置
     *
     * @param ipcseelocation
     * @return
     */
    Message deleteIpcseelocation(Ipcseelocation ipcseelocation);

    /**
     * 获取摄像机位置
     *
     * @param ipcseelocation
     * @return
     */
    Message getIpcseelocation(Ipcseelocation ipcseelocation);

    /**
     * 添加屏幕分别率
     *
     * @param screenresolving
     * @return
     */
    Message addScreenresolving(Screenresolving screenresolving);

    /**
     * 修改屏幕分别率
     *
     * @param screenresolving
     * @return
     */
    Message updateScreenresolving(Screenresolving screenresolving);

    /**
     * 删除屏幕分别率
     *
     * @param screenresolving
     * @return
     */
    Message deleteScreenresolving(Screenresolving screenresolving);

    /**
     * 获取屏幕分辨率
     *
     * @param screenresolving
     * @return
     */
    Message getScreenresolving(Screenresolving screenresolving);
}
