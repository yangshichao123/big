package com.data.big.service;

import com.data.big.model.Ipcseelocation;
import com.data.big.model.Screenresolving;
import com.data.big.model.Trainloaction;
import com.data.big.vo.Message;

public interface ServiceTrain {

    /**
     * 增加列车追踪
     *
     * @param trainloaction
     * @return
     */
    Message addTrainloaction(Trainloaction trainloaction);

    /**
     * 修改列车追踪
     *
     * @param trainloaction
     * @return
     */
    Message updateTrainloaction(Trainloaction trainloaction);

    /**
     * 删除列车追踪
     *
     * @param trainloaction
     * @return
     */
    Message deleteTrainloaction(Trainloaction trainloaction);

    /**
     * 查询列车追踪
     *
     * @param trainloaction
     * @return
     */
    Message getTrainloaction(Trainloaction trainloaction);
}
