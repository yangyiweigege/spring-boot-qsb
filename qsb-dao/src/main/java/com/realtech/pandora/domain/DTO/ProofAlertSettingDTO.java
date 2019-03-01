package com.realtech.pandora.domain.DTO;

import java.util.List;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.realtech.pandora.domain.Person;

@Table(name = "t_qms_proof_alert_setting")
public class ProofAlertSettingDTO {
	@JsonInclude(Include.NON_NULL)
    private String id;
    @JsonInclude(Include.NON_NULL)
    private Byte type;

    @JsonInclude(Include.NON_NULL)
    private String proofDeviceId;

    @JsonInclude(Include.NON_NULL)
    private long notifyTimeLimit;

    @JsonInclude(Include.NON_NULL)
    private Integer notifyTimeLimitUom;

    @JsonInclude(Include.NON_NULL)
    private String factoryId;

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
    
    @JsonInclude(Include.NON_NULL)
    private List<String> persons;
    
    @JsonInclude(Include.NON_NULL)
	private List<Person> personList;//人员id,人员

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getProofDeviceId() {
		return proofDeviceId;
	}

	public void setProofDeviceId(String proofDeviceId) {
		this.proofDeviceId = proofDeviceId;
	}

	public long getNotifyTimeLimit() {
		return notifyTimeLimit;
	}

	public void setNotifyTimeLimit(long notifyTimeLimit) {
		this.notifyTimeLimit = notifyTimeLimit;
	}

	public Integer getNotifyTimeLimitUom() {
		return notifyTimeLimitUom;
	}

	public void setNotifyTimeLimitUom(Integer notifyTimeLimitUom) {
		this.notifyTimeLimitUom = notifyTimeLimitUom;
	}

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}

	public String getDeleteBy() {
		return deleteBy;
	}

	public void setDeleteBy(String deleteBy) {
		this.deleteBy = deleteBy;
	}

	public Integer getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<String> getPersons() {
		return persons;
	}

	public void setPersons(List<String> persons) {
		this.persons = persons;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
    
    
    
}