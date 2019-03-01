package com.realtech.pandora.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Table(name = "t_qms_proof_device")
public class ProofDevice {
    @Id
    private String id;
    private String name;
    private String code;
    private String process;
    @Column(name = "proof_type")
    private Byte proofType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "equipment_id")
    private String equipmentId;
    private Byte status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "installation_time")
    private String installationTime;
    @Column(name = "is_item")
    private Byte isItem;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "maintenance_time")
    private Integer maintenanceTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "maintenance_time_unit")
    private Byte maintenanceTimeUnit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "maintenance_time_num")
    private Integer maintenanceTimeNum;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "verification_time")
    private Integer verificationTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "verification_time_unit")
    private Byte verificationTimeUnit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "verification_time_num")
    private Integer verificationTimeNum;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "verification_method")
    private Byte verificationMethod;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "proof_category")
    private Byte proofCategory;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "factory_id")
    private String factoryId;
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
    @Column(name = "failure_mode")
    private String failureMode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String purpose;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "proof_device_desc")
    private String desc;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String note;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reportTime;
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
     * @return process
     */
    public String getProcess() {
        return process;
    }

    /**
     * @param process
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * @return proof_type
     */
    public Byte getProofType() {
        return proofType;
    }

    /**
     * @param proofType
     */
    public void setProofType(Byte proofType) {
        this.proofType = proofType;
    }

    /**
     * @return equipment_id
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * @param equipmentId
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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
     * @return installation_time
     */
    public String getInstallationTime() {
        return installationTime;
    }

    /**
     * @param installationTime
     */
    public void setInstallationTime(String installationTime) {
        this.installationTime = installationTime;
    }

    /**
     * @return is_item
     */
    public Byte getIsItem() {
        return isItem;
    }

    /**
     * @param isItem
     */
    public void setIsItem(Byte isItem) {
        this.isItem = isItem;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * @return maintenance_time
     */
    public Integer getMaintenanceTime() {
        return maintenanceTime;
    }

    /**
     * @param maintenanceTime
     */
    public void setMaintenanceTime(Integer maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    /**
     * @return maintenance_time_unit
     */
    public Byte getMaintenanceTimeUnit() {
        return maintenanceTimeUnit;
    }

    /**
     * @param maintenanceTimeUnit
     */
    public void setMaintenanceTimeUnit(Byte maintenanceTimeUnit) {
        this.maintenanceTimeUnit = maintenanceTimeUnit;
    }

    /**
     * @return maintenance_time_num
     */
    public Integer getMaintenanceTimeNum() {
        return maintenanceTimeNum;
    }

    /**
     * @param maintenanceTimeNum
     */
    public void setMaintenanceTimeNum(Integer maintenanceTimeNum) {
        this.maintenanceTimeNum = maintenanceTimeNum;
    }

    /**
     * @return verification_time
     */
    public Integer getVerificationTime() {
        return verificationTime;
    }

    /**
     * @param verificationTime
     */
    public void setVerificationTime(Integer verificationTime) {
        this.verificationTime = verificationTime;
    }

    /**
     * @return verification_time_unit
     */
    public Byte getVerificationTimeUnit() {
        return verificationTimeUnit;
    }

    /**
     * @param verificationTimeUnit
     */
    public void setVerificationTimeUnit(Byte verificationTimeUnit) {
        this.verificationTimeUnit = verificationTimeUnit;
    }

    /**
     * @return verification_time_num
     */
    public Integer getVerificationTimeNum() {
        return verificationTimeNum;
    }

    /**
     * @param verificationTimeNum
     */
    public void setVerificationTimeNum(Integer verificationTimeNum) {
        this.verificationTimeNum = verificationTimeNum;
    }

    /**
     * @return verification_method
     */
    public Byte getVerificationMethod() {
        return verificationMethod;
    }

    /**
     * @param verificationMethod
     */
    public void setVerificationMethod(Byte verificationMethod) {
        this.verificationMethod = verificationMethod;
    }

    /**
     * @return proof_category
     */
    public Byte getProofCategory() {
        return proofCategory;
    }

    /**
     * @param proofCategory
     */
    public void setProofCategory(Byte proofCategory) {
        this.proofCategory = proofCategory;
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
     * @return failure_mode
     */
    public String getFailureMode() {
        return failureMode;
    }

    /**
     * @param failureMode
     */
    public void setFailureMode(String failureMode) {
        this.failureMode = failureMode;
    }

    /**
     * @return purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
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