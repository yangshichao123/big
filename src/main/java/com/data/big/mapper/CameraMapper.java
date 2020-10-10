package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Camera;

public interface CameraMapper extends BaseMapper<Camera> {
    int insert(Camera record);

    int insertSelective(Camera record);

    Camera selectByPrimaryId(String deviceId);

    /**
     * 查询里公里标最小的摄像机id
     * @param k 公里标
     * @return
     */
    Camera getMinVideoCode(String k);

    /**
     * 查询里公里标最大的摄像机id
     * @param  k 公里标
     * @return
     */
    Camera getMxnVideoCode(String k);
}