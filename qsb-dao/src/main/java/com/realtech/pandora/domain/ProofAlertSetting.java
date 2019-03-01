package com.realtech.pandora.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_qms_proof_alert_setting")
public class ProofAlertSetting {
    @Id
    private String id;

    private Byte type;

    @Column(name = "proof_device_id")
    private String proofDeviceId;

    @Column(name = "notify_time_limit")
    private BigDecimal notifyTimeLimit;

    @Column(name = "notify_time_limit_uom")
    private Integer notifyTimeLimitUom;

    @Column(name = "factory_id")
    private String factoryId;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_at")
    private String updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "delete_at")
    private String deleteAt;

    @Column(name = "delete_by")
    private String deleteBy;

    @Column(name = "data_version")
    private Integer dataVersion;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    private String note;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return proof_device_id
     */
    public String getProofDeviceId() {
        return proofDeviceId;
    }

    /**
     * @param proofDeviceId
     */
    public void setProofDeviceId(String proofDeviceId) {
        this.proofDeviceId = proofDeviceId;
    }

    /**
     * @return notify_time_limit
     */
    public BigDecimal getNotifyTimeLimit() {
        return notifyTimeLimit;
    }

    /**
     * @param notifyTimeLimit
     */
    public void setNotifyTimeLimit(BigDecimal notifyTimeLimit) {
        this.notifyTimeLimit = notifyTimeLimit;
    }

    /**
     * @return notify_time_limit_uom
     */
    public Integer getNotifyTimeLimitUom() {
        return notifyTimeLimitUom;
    }

    /**
     * @param notifyTimeLimitUom
     */
    public void setNotifyTimeLimitUom(Integer notifyTimeLimitUom) {
        this.notifyTimeLimitUom = notifyTimeLimitUom;
    }

    /**
     * @return factory_id
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * @param factoryId
     */
    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * @return create_at
     */
    public String getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @return update_at
     */
    public String getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt
     */
    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * @return update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return delete_at
     */
    public String getDeleteAt() {
        return deleteAt;
    }

    /**
     * @param deleteAt
     */
    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt;
    }

    /**
     * @return delete_by
     */
    public String getDeleteBy() {
        return deleteBy;
    }

    /**
     * @param deleteBy
     */
    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    /**
     * @return data_version
     */
    public Integer getDataVersion() {
        return dataVersion;
    }

    /**
     * @param dataVersion
     */
    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    /**
     * @return delete_flag
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }
}