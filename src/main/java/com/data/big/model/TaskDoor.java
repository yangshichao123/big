package com.data.big.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
@Table(name = "task_door")
@Data
public class TaskDoor implements Serializable {
    /**
     * 
     */
    @Id
    private Integer id;

    /**
     * 门id
     */
    private String fJwtdmid;

    /**
     * 关联施工和维修表id
     */
    private String tableId;

    /**
     * 作业开始时间
     */
    private String kssj;

    /**
     * 0未执行 1已执行 2没有摄像头信息
     */
    private String status;

    /**
     * 类型 施工或维修
     */
    private String type;


    /**
     * 名称
     */
    private String tdmname;


    /**
     * 类型 施工或维修
     */
    @Transient
    private String ipcid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table task_door
     *
     * @mbg.generated Mon Dec 07 14:13:48 CST 2020
     */
    private static final long serialVersionUID = 1L;


}