package com.realtech.pandora.service;

import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.LoginUserInfo;
import com.realtech.pandora.domain.Person;
import com.realtech.pandora.domain.MeasureTemplate;
import com.realtech.pandora.domain.ProofDevice;
import com.realtech.pandora.domain.page.PageBean;

import java.util.List;

/**
 * 防错装置业务逻辑层
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:40:35
 */
public interface ProofDeviceService {

    /**
     * 根据条件查询防错记录
     * @param proofDeviceDTO
     * @param page
     * @return
     */
    public List<ProofDeviceDTO> queryErrorRecord(ProofDeviceDTO proofDeviceDTO, PageBean page);

    /**
     * 添加防错装置
     * @param proofDevice
     * @param measureTemplates
     * @param persons
     * @param loginUserInfo 
     * @return
     */
    public ResultStatus addProofDevice(ProofDevice proofDevice, List<MeasureTemplate> measureTemplates, List<String> persons, LoginUserInfo loginUserInfo);

    /**
     * 根据名字模糊查询人员表
     * @param name
     * @param page
     * @return
     */
    public List<Person> queryPersonList(String name, PageBean page);

    /**
     * 编辑防错装置
     * @param proofDevice
     * @param measureTemplates
     * @param persons
     * @param loginUserInfo 
     * @return
     */
    public ResultStatus editProofDevice(ProofDevice proofDevice, List<MeasureTemplate> measureTemplates, List<String> persons, LoginUserInfo loginUserInfo);

    /**
     * 根据防错装置id删除防错装置
     * @param id
     * @return
     */
	public ResultStatus deleteProofDeviceById(String id);

    /**
     * 防错清单导出数据
     * @return
     */
    public List<ProofDeviceDTO> exportProofList(String factoryId);

    /**
     * 查询某个设备下所有防错节点状态(防错地图设备层-列表)
     * @param id
     * @param pageBean
     * @return
     */
	public PageBean<ProofDeviceDTO> findBindProofDevice(String id, PageBean<ProofDeviceDTO> pageBean);

    /**
     * 通过设备code查找防错装置
     * @param code
     * @return
     */
	public List<ProofDevice> findProofDeviceByCode(String code);

    /**
     * 通过防错装置id联表查询详情
     * @param id
     * @return
     */
	public List<ProofDeviceDTO> findProofDeviceById(String id);
}
