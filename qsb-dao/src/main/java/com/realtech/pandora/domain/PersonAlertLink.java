package com.realtech.pandora.domain;

import javax.persistence.*;

@Table(name = "t_qms_person_alert_link")
public class PersonAlertLink {
    @Id
    private String id;

    @Column(name = "alert_id")
    private String alertId;

    @Column(name = "person_id")
    private String personId;

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
     * @return alert_id
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * @param alertId
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * @return person_id
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * @param personId
     */
    public void setPersonId(String personId) {
        this.personId = personId;
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