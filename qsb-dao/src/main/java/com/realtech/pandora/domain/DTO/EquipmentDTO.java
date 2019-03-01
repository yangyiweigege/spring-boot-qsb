package com.realtech.pandora.domain.DTO;

import java.math.BigDecimal;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class EquipmentDTO {

	@JsonInclude(Include.NON_NULL)
    private String id;
	@JsonInclude(Include.NON_NULL)
    private String code;
	@JsonInclude(Include.NON_NULL)
    private String name;
	@JsonInclude(Include.NON_NULL)
    private String localName;
	@JsonInclude(Include.NON_NULL)
    private Integer type;
	@JsonInclude(Include.NON_NULL)
    private String factoryLocationId;
	@JsonInclude(Include.NON_NULL)
    private Byte status;
	@JsonInclude(Include.NON_NULL)
    private String manufacturer;
	@JsonInclude(Include.NON_NULL)
    private String modelId;
	@JsonInclude(Include.NON_NULL)
    private String manufactureDate;
	@JsonInclude(Include.NON_NULL)
    private String startToUseDate;
	@JsonInclude(Include.NON_NULL)
    private String endToUseDate;

	@JsonInclude(Include.NON_NULL)
    private BigDecimal ratedCapacity;
	@JsonInclude(Include.NON_NULL)
    private BigDecimal ratedProductionBeating;

	@JsonInclude(Include.NON_NULL)
    private String productionBeatingUomId;

	@JsonInclude(Include.NON_NULL)
    private String ipAddress;

	@JsonInclude(Include.NON_NULL)
    private String manufacturerEquipmentCode;
	@JsonInclude(Include.NON_NULL)
    private BigDecimal lifetime;

	@JsonInclude(Include.NON_NULL)
    private BigDecimal purchasePrice;

	@JsonInclude(Include.NON_NULL)
    private BigDecimal accumulatedDepreciation;
	@JsonInclude(Include.NON_NULL)
    private BigDecimal weight;

	@JsonInclude(Include.NON_NULL)
    private Byte isSpecial;
	@JsonInclude(Include.NON_NULL)
    private BigDecimal power;

	@JsonInclude(Include.NON_NULL)
    private String powerUomId;

	@JsonInclude(Include.NON_NULL)
    private String pictureGroupId;

	@JsonInclude(Include.NON_NULL)
    private String manufacturerCode;

	@JsonInclude(Include.NON_NULL)
    private Integer associatedObjId;

	@JsonInclude(Include.NON_NULL)
    private String createAt;

	@JsonInclude(Include.NON_NULL)
    private String createBy;

	@JsonInclude(Include.NON_NULL)
    private String updateAt;

	@JsonInclude(Include.NON_NULL)
    private String updateBy;

	@JsonInclude(Include.NON_NULL)
    private String deleteAt;

	@JsonInclude(Include.NON_NULL)
    private String deleteBy;

	@JsonInclude(Include.NON_NULL)
    private Integer dataVersion;

	@JsonInclude(Include.NON_NULL)
    private Integer deleteFlag;
	@JsonInclude(Include.NON_NULL)
    private String note;
    
    //防错装置节点异常个数
	@JsonInclude(Include.NON_NULL)
    private Integer exceptionCount;
    
    //设备型号
	@JsonInclude(Include.NON_NULL)
    private String deviceModel;
    
    //当前状态
	@JsonInclude(Include.NON_NULL)
    private String currentStatus;

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
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
     * @return ip_address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
     * @return picture_group_id
     */
    public String getPictureGroupId() {
        return pictureGroupId;
    }

    /**
     * @param pictureGroupId
     */
    public void setPictureGroupId(String pictureGroupId) {
        this.pictureGroupId = pictureGroupId;
    }

    /**
     * @return manufacturer_code
     */
    public String getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * @param manufacturerCode
     */
    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
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

	public Integer getExceptionCount() {
		return exceptionCount;
	}

	public void setExceptionCount(Integer exceptionCount) {
		this.exceptionCount = exceptionCount;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
    
    
}