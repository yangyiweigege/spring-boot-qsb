package com.realtech.pandora.domain.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class ProofMapDTO {

	@JsonInclude(Include.NON_NULL)
	private String id;

	@JsonInclude(Include.NON_NULL)
	private String pid;

	@JsonInclude(Include.NON_NULL)
	private String objectId;
	@JsonInclude(Include.NON_NULL)
	private Byte type;
	@JsonInclude(Include.NON_NULL)
	private String flag;
	@JsonInclude(Include.NON_NULL)
	private String resourceId;
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
	private Integer status;
	@JsonInclude(Include.NON_NULL)
	private String color;


}
