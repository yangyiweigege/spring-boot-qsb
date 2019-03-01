package com.realtech.pandora.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Table(name = "t_qms_measure_record")
public class MeasureRecord {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "proof_device_id")
    private String proofDeviceId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "reporter_person_user_obj_id")
    private String reporterPersonUserObjId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "report_time")
    private String reportTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "fixer_person_user_obj_id")
    private String fixerPersonUserObjId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "fix_time")
    private String fixTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer bach;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Byte terminal;
    @Column(name = "is_qualified")
    private Byte isQualified;
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
    @Column(name = "fault_performance")
    private String faultPerformance;
    @Column(name = "emergency_response")
    private String emergencyResponse;
    @Column(name = "verification_item")
    private String verificationItem;
    @Column(name = "verification_content")
    private String verificationContent;
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
     * @return reporter_person_user_obj_id
     */
    public String getReporterPersonUserObjId() {
        return reporterPersonUserObjId;
    }

    /**
     * @param reporterPersonUserObjId
     */
    public void setReporterPersonUserObjId(String reporterPersonUserObjId) {
        this.reporterPersonUserObjId = reporterPersonUserObjId;
    }

    /**
     * @return report_time
     */
    public String getReportTime() {
        return reportTime;
    }

    /**
     * @param reportTime
     */
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * @return fixer_person_user_obj_id
     */
    public String getFixerPersonUserObjId() {
        return fixerPersonUserObjId;
    }

    /**
     * @param fixerPersonUserObjId
     */
    public void setFixerPersonUserObjId(String fixerPersonUserObjId) {
        this.fixerPersonUserObjId = fixerPersonUserObjId;
    }

    /**
     * @return fix_time
     */
    public String getFixTime() {
        return fixTime;
    }

    /**
     * @param fixTime
     */
    public void setFixTime(String fixTime) {
        this.fixTime = fixTime;
    }

    /**
     * @return bach
     */
    public Integer getBach() {
        return bach;
    }

    /**
     * @param bach
     */
    public void setBach(Integer bach) {
        this.bach = bach;
    }

    /**
     * @return terminal
     */
    public Byte getTerminal() {
        return terminal;
    }

    /**
     * @param terminal
     */
    public void setTerminal(Byte terminal) {
        this.terminal = terminal;
    }

    /**
     * @return is_qualified
     */
    public Byte getIsQualified() {
        return isQualified;
    }

    /**
     * @param isQualified
     */
    public void setIsQualified(Byte isQualified) {
        this.isQualified = isQualified;
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
     * @return fault_performance
     */
    public String getFaultPerformance() {
        return faultPerformance;
    }

    /**
     * @param faultPerformance
     */
    public void setFaultPerformance(String faultPerformance) {
        this.faultPerformance = faultPerformance;
    }

    /**
     * @return emergency_response
     */
    public String getEmergencyResponse() {
        return emergencyResponse;
    }

    /**
     * @param emergencyResponse
     */
    public void setEmergencyResponse(String emergencyResponse) {
        this.emergencyResponse = emergencyResponse;
    }

    /**
     * @return verification_item
     */
    public String getVerificationItem() {
        return verificationItem;
    }

    /**
     * @param verificationItem
     */
    public void setVerificationItem(String verificationItem) {
        this.verificationItem = verificationItem;
    }

    /**
     * @return verification_content
     */
    public String getVerificationContent() {
        return verificationContent;
    }

    /**
     * @param verificationContent
     */
    public void setVerificationContent(String verificationContent) {
        this.verificationContent = verificationContent;
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