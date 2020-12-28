package com.data.big.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "hcsj")
@Data
public class Hcsj implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 单位
     */
    private String dwmc;

    /**
     * 线名
     */
    private String xm;

    /**
     * 线编号
     */
    private String xbh;

    /**
     * 行别
     */
    private String xb;

    /**
     * 里程
     */
    private String lc;

    /**
     * 检测时间
     */
    private String jcsj;

    /**
     * 接收时间
     */
    private String jssj;

    /**
     * 垂加
     */
    private String czjsd;

    /**
     * 垂加级别
     */
    private String czjsdjb;

    /**
     * 水加
     */
    private String spjsd;

    /**
     * 水加级别
     */
    private String spjsdjb;

    /**
     * 速度
     */
    private String sd;

    /**
     * 机车型号
     */
    private String jcxh;

    /**
     * 机车号
     */
    private String jch;

    /**
     * 车次
     */
    private String cc;

    /**
     * 添加任务状态 0未添加 1以添加
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hcsj
     *
     * @mbg.generated Tue Oct 13 08:55:08 CST 2020
     */
    private static final long serialVersionUID = 1L;


}