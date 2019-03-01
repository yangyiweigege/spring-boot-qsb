package com.realtech.pandora.common.enums;

/**
 * Created by zhuqq
 */
public enum VerificationEnum {

	//防错地图 type字段
	PROOF_MAP_DEVICE_TYPE(0, "设备层"),
	PROOF_MAP_FACTORY_TYPE(1, "非设备层(工段层)"),

    //防错类型
    ErrorProofType_ALL(-1, "全部"),
    ErrorProofType_DEFEND(0, "预防式"),
    ErrorProofType_SEARCH(1, "探测式"),

    //是否点检项目
    IfCheckItem_ALL(-1, "全部"),
    IfCheckItem_NO(0, "否"),
    IfCheckItem_YES(1, "是"),

    //当前状态
    TempStates_ALL(-1, "全部"),
    TempStates_ONGOING(0, "运行中"),
    TempStates_LOSE_EFFICACY(1, "已失效"),

    //保养频率
    CareRates_DAY(0, "天"),
    CareRates_WEEK(1, "周"),
    CareRates_MONTH(2, "月"),

    //验证频率
    IdentifyRates_DAY(0, "天"),
    IdentifyRates_WEEK(1, "周"),
    IdentifyRates_MONTH(2, "月"),
    IdentifyRates_HOUR(3, "时"),
    IdentifyRates_MINUTE(4, "分"),
    IdentifyRates_SECOND(5, "秒"),

    //验证方法
    IdentifyMethods_ARTIFICIAL(0, "人工验证"),
    IdentifyMethods_AUTO(1, "自动验证"),

    //防错类别
    ErrorProofClass_ARTIFICIAL(0, "产品防错"),
    ErrorProofClass_AUTO(1, "安全防错"),

    //超时设置周期
    TimeoutPeriod_DAY(0, "天"),
    TimeoutPeriod_HOUR(0, "时"),
    TimeoutPeriod_MINUTE(0, "分"),

    //删除状态
    DELETE_YES(1, "已删除"),
    DELETE_NO(0, "未删除"),

    //来源
    SOURCE_WEB(0, "web端"),
    SOURCE_TERMINAL(1, "终端"),

    QUALIFIED_YES(1, "合格"),
    QUALIFIED_NO(0, "不合格"),
    //集团用户专属工厂id
    FACTORY_ID(0, "JQ303EHVOAEH5NBZ45CR"),
    ;

    private int code;

    private String msg;

    VerificationEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
