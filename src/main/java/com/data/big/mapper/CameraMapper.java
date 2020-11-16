package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Camera;

import java.util.List;

public interface CameraMapper extends BaseMapper<Camera> {
    int insert(Camera record);

    int insertSelective(Camera record);

    Camera selectByPrimaryId(String deviceId);

    Camera getMinVideoCode(int k);

    Camera getMxnVideoCode(int parseInt);

    List<Camera> getVideoCode(Integer k, Integer j);

    List<Camera> selectAllCamera(Camera camera);

    /**
     * 通过开始结束公里标 查找离最近的两个和中间的摄像机
     * @return
     */
    List<Camera> getVideoByKJ(int k, int j);
}