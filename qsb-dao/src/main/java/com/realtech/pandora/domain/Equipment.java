package com.realtech.pandora.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_bd_equipment")
public class Equipment {
	@Id
    private String id;

    private String code;

    private String name;

    @Column(name = "local_name")
    private String localName;

    @Column(name = "factory_location_id")
    private String factoryLocationId;

    private Byte status;

    @Column(name = "model_id")
    private String modelId;

    @Column(name = "manufacture_date")
    private String manufactureDate;

    @Column(name = "start_to_use_date")
    private String startToUseDate;

    @Column(name = "end_to_use_date")
    private String endToUseDate;

    @Column(name = "rated_capacity")
    private BigDecimal ratedCapacity;

    @Column(name = "rated_production_beating")
    private BigDecimal ratedProductionBeating;

    @Column(name = "production_beating_uom_id")
    private String productionBeatingUomId;

    private BigDecimal lifetime;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Column(name = "accumulated_depreciation")
    private BigDecimal accumulatedDepreciation;

    @Column(name = "is_special")
    private Byte isSpecial;

    private BigDecimal power;

    @Column(name = "power_uom_id")
    private String powerUomId;

    @Column(name = "associated_obj_id")
    private Integer associatedObjId;

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

    @Column(name = "manufacturer_id")
    private String manufacturerId;

    @Column(name = "manufacturer_equipment_code")
    private String manufacturerEquipmentCode;

    @Column(name = "photo_res_user_obj_id")
    private String photoResUserObjId;

    @Column(name = "factory_id")
    private String factoryId;

    @Column(name = "type_id")
    private String typeId;

    private Byte classification;

    @Column(name = "user_department_id")
    private String userDepartmentId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "depreciation_rate")
    private BigDecimal depreciationRate;

    @Column(name = "residual_value")
    private BigDecimal residualValue;

    private BigDecimal weight;

    @Column(name = "installation_date")
    private String installationDate;

    @Column(name = "acceptance_date")
    private String acceptanceDate;

    @Column(name = "qr_code_picture_url")
    private String qrCodePictureUrl;

    @Column(name = "acceptance_application_form_id")
    private String acceptanceApplicationFormId;

    @Column(name = "equipment_resume_id")
    private String equipmentResumeId;

    private String note;

    private String description;

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
     * @return factory_location_id
     */
    public String getFactoryLocationId() {
        return factoryLocationId;
    }

    /**
     * @param factoryLocationId
     */
    public void setFactoryLocationId(String factoryLocationId) {
        this.factoryLocationId = factoryLocationId;
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
     * @return model_id
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * @param modelId
     */
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    /**
     * @return manufacture_date
     */
    public String getManufactureDate() {
        return manufactureDate;
    }

    /**
     * @param manufactureDate
     */
    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     * @return start_to_use_date
     */
    public String getStartToUseDate() {
        return startToUseDate;
    }

    /**
     * @param startToUseDate
     */
    public void setStartToUseDate(String startToUseDate) {
        this.startToUseDate = startToUseDate;
    }

    /**
     * @return end_to_use_date
     */
    public String getEndToUseDate() {
        return endToUseDate;
    }

    /**
     * @param endToUseDate
     */
    public void setEndToUseDate(String endToUseDate) {
        this.endToUseDate = endToUseDate;
    }

    /**
     * @return rated_capacity
     */
    public BigDecimal getRatedCapacity() {
        return ratedCapacity;
    }

    /**
     * @param ratedCapacity
     */
    public void setRatedCapacity(BigDecimal ratedCapacity) {
        this.ratedCapacity = ratedCapacity;
    }

    /**
     * @return rated_production_beating
     */
    public BigDecimal getRatedProductionBeating() {
        return ratedProductionBeating;
    }

    /**
     * @param ratedProductionBeating
     */
    public void setRatedProductionBeating(BigDecimal ratedProductionBeating) {
        this.ratedProductionBeating = ratedProductionBeating;
    }

    /**
     * @return production_beating_uom_id
     */
    public String getProductionBeatingUomId() {
        return productionBeatingUomId;
    }

    /**
     * @param productionBeatingUomId
     */
    public void setProductionBeatingUomId(String productionBeatingUomId) {
        this.productionBeatingUomId = productionBeatingUomId;
    }

    /**
     * @return lifetime
     */
    public BigDecimal getLifetime() {
        return lifetime;
    }

    /**
     * @param lifetime
     */
    public void setLifetime(BigDecimal lifetime) {
        this.lifetime = lifetime;
    }

    /**
     * @return purchase_price
     */
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * @param purchasePrice
     */
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * @return accumulated_depreciation
     */
    public BigDecimal getAccumulatedDepreciation() {
        return accumulatedDepreciation;
    }

    /**
     * @param accumulatedDepreciation
     */
    public void setAccumulatedDepreciation(BigDecimal accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }

    /**
     * @return is_special
     */
    public Byte getIsSpecial() {
        return isSpecial;
    }

    /**
     * @param isSpecial
     */
    public void setIsSpecial(Byte isSpecial) {
        this.isSpecial = isSpecial;
    }

    /**
     * @return power
     */
    public BigDecimal getPower() {
        return power;
    }

    /**
     * @param power
     */
    public void setPower(BigDecimal power) {
        this.power = power;
    }

    /**
     * @return power_uom_id
     */
    public String getPowerUomId() {
        return powerUomId;
    }

    /**
     * @param powerUomId
     */
    public void setPowerUomId(String powerUomId) {
        this.powerUomId = powerUomId;
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
     * @return manufacturer_id
     */
    public String getManufacturerId() {
        return manufacturerId;
    }

    /**
     * @param manufacturerId
     */
    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * @return manufacturer_equipment_code
     */
    public String getManufacturerEquipmentCode() {
        return manufacturerEquipmentCode;
    }

    /**
     * @param manufacturerEquipmentCode
     */
    public void setManufacturerEquipmentCode(String manufacturerEquipmentCode) {
        this.manufacturerEquipmentCode = manufacturerEquipmentCode;
    }

    /**
     * @return photo_res_user_obj_id
     */
    public String getPhotoResUserObjId() {
        return photoResUserObjId;
    }

    /**
     * @param photoResUserObjId
     */
    public void setPhotoResUserObjId(String photoResUserObjId) {
        this.photoResUserObjId = photoResUserObjId;
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
     * @return type_id
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * @return classification
     */
    public Byte getClassification() {
        return classification;
    }

    /**
     * @param classification
     */
    public void setClassification(Byte classification) {
        this.classification = classification;
    }

    /**
     * @return user_department_id
     */
    public String getUserDepartmentId() {
        return userDepartmentId;
    }

    /**
     * @param userDepartmentId
     */
    public void setUserDepartmentId(String userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return depreciation_rate
     */
    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    /**
     * @param depreciationRate
     */
    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    /**
     * @return residual_value
     */
    public BigDecimal getResidualValue() {
        return residualValue;
    }

    /**
     * @param residualValue
     */
    public void setResidualValue(BigDecimal residualValue) {
        this.residualValue = residualValue;
    }

    /**
     * @return weight
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * @return installation_date
     */
    public String getInstallationDate() {
        return installationDate;
    }

    /**
     * @param installationDate
     */
    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    /**
     * @return acceptance_date
     */
    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    /**
     * @param acceptanceDate
     */
    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    /**
     * @return qr_code_picture_url
     */
    public String getQrCodePictureUrl() {
        return qrCodePictureUrl;
    }

    /**
     * @param qrCodePictureUrl
     */
    public void setQrCodePictureUrl(String qrCodePictureUrl) {
        this.qrCodePictureUrl = qrCodePictureUrl;
    }

    /**
     * @return acceptance_application_form_id
     */
    public String getAcceptanceApplicationFormId() {
        return acceptanceApplicationFormId;
    }

    /**
     * @param acceptanceApplicationFormId
     */
    public void setAcceptanceApplicationFormId(String acceptanceApplicationFormId) {
        this.acceptanceApplicationFormId = acceptanceApplicationFormId;
    }

    /**
     * @return equipment_resume_id
     */
    public String getEquipmentResumeId() {
        return equipmentResumeId;
    }

    /**
     * @param equipmentResumeId
     */
    public void setEquipmentResumeId(String equipmentResumeId) {
        this.equipmentResumeId = equipmentResumeId;
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
}