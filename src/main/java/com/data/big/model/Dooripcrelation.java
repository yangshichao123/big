package com.data.big.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
@Table(name = "dooripcrelation")
@Data
public class Dooripcrelation implements Serializable {
    /**
     * 
     */
    @Id
    private String doorid;

    /**
     * 
     */
    private String xbh;

    /**
     * 
     */
    private String xm;

    /**
     * 
     */
    private String lc;

    /**
     * 
     */
    private String ipcid;

    /**
     * 
     */
    private String ipcname;

    /**
     * 
     */
    private String bh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dooripcrelation
     *
     * @mbg.generated Mon Dec 07 14:13:25 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页编号
     */
    @Transient
    private String pageIndex;
    /**
     * 每页个数
     */
    @Transient
    private String pageSize;


}