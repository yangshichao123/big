package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "role")
public class Role implements Serializable {
    /**
     * 序号
     */
    @Id
    private Integer id;

    /**
     * 角色编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String descs;

    /**
     * 角色启用状态,0未启用，1启用
     */
    private Integer activeState;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 角色权限  模块id 多个以；号 分割
     */
    private String jurisdiction;

    /**
     * 模块id list
     */
    @Transient
    private List<Integer> modularIds;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table role
     *
     * @mbg.generated Mon Oct 19 10:59:38 CST 2020
     */
    private static final long serialVersionUID = 1L;


    public List<Integer> getModularIds() {
        return modularIds;
    }

    public void setModularIds(List<Integer> modularIds) {
        this.modularIds = modularIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }

    public Integer getActiveState() {
        return activeState;
    }

    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction == null ? null : jurisdiction.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", descs=").append(descs);
        sb.append(", activeState=").append(activeState);
        sb.append(", createtime=").append(createtime);
        sb.append(", jurisdiction=").append(jurisdiction);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}