package com.realtech.pandora.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_uums_user")
public class UUMSUser {
    @Id
    private String id;

    private String name;

    @Column(name = "local_name")
    private String localName;

    private String description;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "user_flag")
    private Byte userFlag;

    @Column(name = "is_lock")
    private Byte isLock;

    @Column(name = "factory_id")
    private String factoryId;

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

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "data_version")
    private Integer dataVersion;

    @Column(name = "expiry_time")
    private String expiryTime;

    @Column(name = "effective_time")
    private String effectiveTime;

    private Byte status;

    private String password;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return local_name
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * @param localName
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return last_login_time
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return user_flag
     */
    public Byte getUserFlag() {
        return userFlag;
    }

    /**
     * @param userFlag
     */
    public void setUserFlag(Byte userFlag) {
        this.userFlag = userFlag;
    }

    /**
     * @return is_lock
     */
    public Byte getIsLock() {
        return isLock;
    }

    /**
     * @param isLock
     */
    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
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
     * @return expiry_time
     */
    public String getExpiryTime() {
        return expiryTime;
    }

    /**
     * @param expiryTime
     */
    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    /**
     * @return effective_time
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * @param effectiveTime
     */
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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