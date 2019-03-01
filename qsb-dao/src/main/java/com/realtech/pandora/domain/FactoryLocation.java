package com.realtech.pandora.domain;

import java.math.BigDecimal;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Table(name = "t_bd_factory_location")
public class FactoryLocation {
    @Id
    private String id;

    @JsonInclude(Include.NON_NULL)
    private String name;

    @JsonInclude(Include.NON_NULL)
    private String code;

    @Column(name = "local_name")
    @JsonInclude(Include.NON_NULL)
    private String localName;

    @JsonInclude(Include.NON_NULL)
    private Byte type;

    @JsonInclude(Include.NON_NULL)
    private Integer level;

    @Column(name = "parent_location_id")
    @JsonInclude(Include.NON_NULL)
    private String parentLocationId;

    @Column(name = "is_root")
    @JsonInclude(Include.NON_NULL)
    private Byte isRoot;

    @Column(name = "logistic_x_coordinate")
    @JsonInclude(Include.NON_NULL)
    private BigDecimal logisticXCoordinate;

    @Column(name = "logistic_y_coordinate")
    @JsonInclude(Include.NON_NULL)
    private BigDecimal logisticYCoordinate;

    @Column(name = "logistic_z_coordinate")
    @JsonInclude(Include.NON_NULL)
    private BigDecimal logisticZCoordinate;

    @Column(name = "factory_id")
    @JsonInclude(Include.NON_NULL)
    private String factoryId;

    @Column(name = "associated_obj_id")
    @JsonInclude(Include.NON_NULL)
    private Integer associatedObjId;

    @Column(name = "create_at")
    @JsonInclude(Include.NON_NULL)
    private String createAt;

    @Column(name = "create_by")
    @JsonInclude(Include.NON_NULL)
    private String createBy;

    @Column(name = "update_at")
    @JsonInclude(Include.NON_NULL)
    private String updateAt;

    @Column(name = "update_by")
    @JsonInclude(Include.NON_NULL)
    private String updateBy;

    @Column(name = "delete_at")
    @JsonInclude(Include.NON_NULL)
    private String deleteAt;

    @Column(name = "delete_by")
    @JsonInclude(Include.NON_NULL)
    private String deleteBy;

    @Column(name = "data_version")
    @JsonInclude(Include.NON_NULL)
    private Integer dataVersion;

    @Column(name = "delete_flag")
    @JsonInclude(Include.NON_NULL)
    private Integer deleteFlag;

    @Column(name = "network_ip")
    @JsonInclude(Include.NON_NULL)
    private String networkIp;

    @JsonInclude(Include.NON_NULL)
    private String description;

    @JsonInclude(Include.NON_NULL)
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
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return parent_location_id
     */
    public String getParentLocationId() {
        return parentLocationId;
    }

    /**
     * @param parentLocationId
     */
    public void setParentLocationId(String parentLocationId) {
        this.parentLocationId = parentLocationId;
    }

    /**
     * @return is_root
     */
    public Byte getIsRoot() {
        return isRoot;
    }

    /**
     * @param isRoot
     */
    public void setIsRoot(Byte isRoot) {
        this.isRoot = isRoot;
    }

    /**
     * @return logistic_x_coordinate
     */
    public BigDecimal getLogisticXCoordinate() {
        return logisticXCoordinate;
    }

    /**
     * @param logisticXCoordinate
     */
    public void setLogisticXCoordinate(BigDecimal logisticXCoordinate) {
        this.logisticXCoordinate = logisticXCoordinate;
    }

    /**
     * @return logistic_y_coordinate
     */
    public BigDecimal getLogisticYCoordinate() {
        return logisticYCoordinate;
    }

    /**
     * @param logisticYCoordinate
     */
    public void setLogisticYCoordinate(BigDecimal logisticYCoordinate) {
        this.logisticYCoordinate = logisticYCoordinate;
    }

    /**
     * @return logistic_z_coordinate
     */
    public BigDecimal getLogisticZCoordinate() {
        return logisticZCoordinate;
    }

    /**
     * @param logisticZCoordinate
     */
    public void setLogisticZCoordinate(BigDecimal logisticZCoordinate) {
        this.logisticZCoordinate = logisticZCoordinate;
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
     * @return network_ip
     */
    public String getNetworkIp() {
        return networkIp;
    }

    /**
     * @param networkIp
     */
    public void setNetworkIp(String networkIp) {
        this.networkIp = networkIp;
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