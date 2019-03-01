package com.realtech.pandora.domain.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by zhuqq
 */
@Data
public class MeasureTemplateDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String proofDeviceId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createBy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updateAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updateBy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deleteAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer dataVersion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer deleteFlag;
    private String faultPerformance;
    private String emergencyResponse;
    private String verificationItem;
    private String verificationContent;
    private String note;
    private Integer isQualified;
}
