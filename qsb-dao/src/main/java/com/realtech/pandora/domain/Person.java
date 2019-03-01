package com.realtech.pandora.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Table(name = "t_bd_person")
public class Person {
    @Id
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "nickle_name")
    private String nickleName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Byte sex;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "photo_url")
    private String photoUrl;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "department_id")
    private String departmentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "position_id")
    private String positionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "skillset_level")
    private Integer skillsetLevel;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "factory_id")
    private String factoryId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "home_address")
    private String homeAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "associated_obj_id")
    private Integer associatedObjId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "create_at")
    private String createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "create_by")
    private String createBy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "update_at")
    private String updateAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "update_by")
    private String updateBy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "delete_at")
    private String deleteAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "delete_by")
    private String deleteBy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "data_version")
    private Integer dataVersion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "delete_flag")
    private Integer deleteFlag;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mobile;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String weixin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "rfid_card_no")
    private String rfidCardNo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "person_attribute")
    private Byte personAttribute;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return nickle_name
     */
    public String getNickleName() {
        return nickleName;
    }

    /**
     * @param nickleName
     */
    public void setNickleName(String nickleName) {
        this.nickleName = nickleName;
    }

    /**
     * @return sex
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return photo_url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * @return department_id
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return position_id
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * @param positionId
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    /**
     * @return skillset_level
     */
    public Integer getSkillsetLevel() {
        return skillsetLevel;
    }

    /**
     * @param skillsetLevel
     */
    public void setSkillsetLevel(Integer skillsetLevel) {
        this.skillsetLevel = skillsetLevel;
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
     * @return home_address
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * @param homeAddress
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * @return associated_obj_id
     */
    public Integer getAssociatedObjId() {
        return associatedObjId;
    }

    /**
     * @param associatedObjId
     */
    public void setAssociatedObjId(Integer associatedObjId) {
        this.associatedObjId = associatedObjId;
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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return weixin
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * @param weixin
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * @return rfid_card_no
     */
    public String getRfidCardNo() {
        return rfidCardNo;
    }

    /**
     * @param rfidCardNo
     */
    public void setRfidCardNo(String rfidCardNo) {
        this.rfidCardNo = rfidCardNo;
    }

    /**
     * @return person_attribute
     */
    public Byte getPersonAttribute() {
        return personAttribute;
    }

    /**
     * @param personAttribute
     */
    public void setPersonAttribute(Byte personAttribute) {
        this.personAttribute = personAttribute;
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