package com.realtech.pandora.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.realtech.pandora.common.utils.LoginUserInfo;
import com.realtech.pandora.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.realtech.pandora.common.enums.VerificationEnum;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.DateCompareUtil;
import com.realtech.pandora.dao.MeasureRecordMapper;
import com.realtech.pandora.dao.PersonMapper;
import com.realtech.pandora.dao.PersonProofLinkMapper;
import com.realtech.pandora.dao.ProofDeviceMapper;
import com.realtech.pandora.domain.DTO.MeasureRecordDTO;
import com.realtech.pandora.domain.page.PageBean;
import com.realtech.pandora.service.MeasureRecordService;
import com.realtech.pandora.util.DateUtil;
import com.realtech.pandora.util.OidUtil;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class MeasureRecordServiceBean implements MeasureRecordService {

	@Resource
	private MeasureRecordMapper measureRecordMapper;

	@Resource
	private PersonMapper personMapper;

	@Resource
	private PersonProofLinkMapper personProofLinkMapper;

	@Resource
	private ProofDeviceMapper proofDeviceMapper;

	@Override
	@Transactional(readOnly = true, isolation=Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<MeasureRecord> findAll() {
		PageHelper.startPage(1, 10, false);
		return measureRecordMapper.selectAll();
	}

	/**
	 * 添加和修改（包括删除）防错措施记录
	 * @param measureRecordDTOList	防错措施记录
	 * @param proofDeviceId 					防错装置id
	 * @param status								防错装置状态
	 * @return											结果状态
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultStatus addMeasureRecord(List<MeasureRecordDTO> measureRecordDTOList, String proofDeviceId, Integer status, LoginUserInfo loginUserInfo) {
		//防错装置状态为运行中
		if (status == VerificationEnum.TempStates_ONGOING.getCode()) {
			if (!CollectionUtils.isEmpty(measureRecordDTOList)) {
				Integer bach = null;
				//对防错措施记录进行批次号查询查出最大批次号
				Integer maxBach = measureRecordMapper.queryMaxBach(proofDeviceId);
				//如果没查到则自己赋值一个初始批次
				if (maxBach == null) {
					bach = 1801;
				} else {
					bach = maxBach+1;
				}
				List<MeasureRecordDTO> addMeasureRecordDTO = new LinkedList<>();
				//判断是否需要修改防错装置状态
				boolean flag = false;
				for (MeasureRecordDTO measureRecordDTO : measureRecordDTOList) {
					if (measureRecordDTO.getTerminal() == VerificationEnum.SOURCE_WEB.getCode()) {
						//设置批次号
						measureRecordDTO.setBach(bach);
						addMeasureRecordDTO.add(measureRecordDTO);
						//如果没有填写修复时间说明还未修复完成，防错装置状态就要改为已失效
						if (StringUtils.isEmpty(measureRecordDTO.getFixTime())) {
							flag = true;
						}
					}
				}

				//最近上报时间
				String reportTime = addRecord(addMeasureRecordDTO, proofDeviceId, bach, loginUserInfo.getUserName());
				//新增防错措施记录表和人员防错关联表
				/*if (!CollectionUtils.isEmpty(addMeasureRecordDTO)) {
					reportTime = addMeasureRecordDTO.get(0).getReportTime();//最近上报时间赋予初值

					for (MeasureRecordDTO measureRecordDTO : addMeasureRecordDTO) {
						MeasureRecord measureRecord = new MeasureRecord();
						measureRecord.setId(OidUtil.newId());
						measureRecord.setProofDeviceId(proofDeviceId);
						measureRecord.setFaultPerformance(measureRecordDTO.getFaultPerformance());
						measureRecord.setEmergencyResponse(measureRecordDTO.getEmergencyResponse());
						measureRecord.setVerificationItem(measureRecordDTO.getVerificationItem());
						measureRecord.setVerificationContent(measureRecordDTO.getVerificationContent());

						//新增上报人员防错关联数据
						if (!StringUtils.isEmpty(measureRecordDTO.getReportPersons())) {
							String objId = OidUtil.newId();
							measureRecord.setReporterPersonUserObjId(objId);
							measureRecord.setReportTime(measureRecordDTO.getReportTime());
							addPersonProofLink(measureRecordDTO.getReportPersons().split(","), objId);
						}
						//新增修复人员防错关联数据
						if (!StringUtils.isEmpty(measureRecordDTO.getFixPersons())) {
							String objId = OidUtil.newId();
							measureRecord.setFixerPersonUserObjId(objId);
							measureRecord.setFixTime(measureRecordDTO.getFixTime());
							addPersonProofLink(measureRecordDTO.getFixPersons().split(","), objId);
						}

						measureRecord.setBach(bach);
						measureRecord.setIsQualified((byte) VerificationEnum.QUALIFIED_NO.getCode());
						measureRecord.setCreateAt(DateUtil.format(new Date()));
						measureRecord.setCreateBy("创建人");
						measureRecord.setDataVersion(1);
						measureRecord.setDeleteFlag(VerificationEnum.DELETE_NO.getCode());
						measureRecord.setTerminal((byte) VerificationEnum.SOURCE_WEB.getCode());
						measureRecordMapper.insertSelective(measureRecord);

						//比较最近上报时间大小
						if (reportTime != null && measureRecordDTO.getReportTime() != null) {
							int flagTime = DateCompareUtil.compareDate(reportTime, measureRecordDTO.getReportTime());
							if (flagTime < 0) reportTime = measureRecordDTO.getReportTime();
						}
					}
				}*/

				//修改防错装置最近上报时间(取最近上报时间最大的)
				updateReportTime(reportTime, proofDeviceId, null);
				/*if (reportTime != null) {
					ProofDevice proofDevice = new ProofDevice();
					proofDevice.setId(proofDeviceId);
					proofDevice.setReportTime(reportTime);
					proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);
				}*/

				//将防错装置改为失效
				if (flag) {
					ProofDevice proofDevice = new ProofDevice();
					proofDevice.setId(proofDeviceId);
					proofDevice.setStatus((byte) VerificationEnum.TempStates_LOSE_EFFICACY.getCode());
					proofDevice.setUpdateAt(DateUtil.format(new Date()));
					proofDevice.setUpdateBy(loginUserInfo.getUserName());
					proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);
				}

			}
		} else {
		//防错装置状态为已失效

			//传来的数据为空说明删除了所有web端新增的数据
			if (CollectionUtils.isEmpty(measureRecordDTOList)) {
				List<MeasureRecordDTO> list = measureRecordMapper.queryMeasureRecordList(proofDeviceId);
				if (!CollectionUtils.isEmpty(list)) {
					MeasureRecord measureRecord = new MeasureRecord();
					measureRecord.setDeleteFlag(VerificationEnum.DELETE_YES.getCode());
					measureRecord.setDeleteBy(loginUserInfo.getUserName());
					measureRecord.setDeleteAt(DateUtil.format(new Date()));
					Example examp = new Example(MeasureRecord.class);
					Example.Criteria crit = examp.createCriteria();
					crit.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
					crit.andEqualTo("proofDeviceId", proofDeviceId);
					crit.andEqualTo("bach", list.get(0).getBach());
					crit.andEqualTo("terminal", VerificationEnum.SOURCE_WEB.getCode());
					//删除当前批次所有web端增加的防错措施记录
					measureRecordMapper.updateByExampleSelective(measureRecord, examp);

					//修改防错装置最近上报时间
					String reportTime = measureRecordMapper.queryReportTime(proofDeviceId);
					proofDeviceMapper.updateReportTime(reportTime, proofDeviceId);

					for (MeasureRecordDTO measureRecordDTO : list) {
						//逻辑删除人员防错关联表（上报）
						Example reportExampl = new Example(PersonProofLink.class);
						Example.Criteria criter = reportExampl.createCriteria();
						criter.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
						criter.andEqualTo("objectId", measureRecordDTO.getReporterPersonUserObjId());
						PersonProofLink ppl = new PersonProofLink();
						ppl.setDeleteFlag(VerificationEnum.DELETE_YES.getCode());
						ppl.setDeleteAt(DateUtil.format(new Date()));
						ppl.setDeleteBy(loginUserInfo.getUserName());
						personProofLinkMapper.updateByExampleSelective(ppl, reportExampl);
						//逻辑删除人员防错关联表（修复）
						Example fixExampl = new Example(PersonProofLink.class);
						Example.Criteria fixCriter = fixExampl.createCriteria();
						fixCriter.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
						fixCriter.andEqualTo("objectId", measureRecordDTO.getFixerPersonUserObjId());
						PersonProofLink fixPpl = new PersonProofLink();
						fixPpl.setDeleteFlag(VerificationEnum.DELETE_YES.getCode());
						fixPpl.setDeleteAt(DateUtil.format(new Date()));
						fixPpl.setDeleteBy(loginUserInfo.getUserName());
						personProofLinkMapper.updateByExampleSelective(fixPpl, fixExampl);
					}
				}

				//将防错装置改为运行中
				ProofDevice proofDevice = new ProofDevice();
				proofDevice.setId(proofDeviceId);
				proofDevice.setStatus((byte) VerificationEnum.TempStates_ONGOING.getCode());
				proofDevice.setUpdateAt(DateUtil.format(new Date()));
				proofDevice.setUpdateBy(loginUserInfo.getUserName());
				proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);
			} else {
				//传来的数据不为空
				Integer bach = null;//批次号
				boolean flag = false;//是否改变防错装置状态
				List<MeasureRecordDTO> addMeasureRecordDTO = new LinkedList<>();
				for (MeasureRecordDTO measureRecordDTO : measureRecordDTOList) {
					if (!StringUtils.isEmpty(measureRecordDTO.getBach())) {
						bach = measureRecordDTO.getBach();
					}
					if (StringUtils.isEmpty(measureRecordDTO.getFixTime())) {
						flag = true;
					}
					if (measureRecordDTO.getTerminal() == VerificationEnum.SOURCE_WEB.getCode()) {
						addMeasureRecordDTO.add(measureRecordDTO);
					}
				}

				if (bach == null) {
					//对防错措施记录进行批次号查询查出最大批次号
					Integer maxBach = measureRecordMapper.queryMaxBach(proofDeviceId);
					//如果没查到则自己赋值一个初始批次
					if (maxBach == null) {
						bach = 1801;
					} else {
						bach = maxBach+1;
					}
				}

				//删除当前批次所有web端增加的防错措施记录
				MeasureRecord record = new MeasureRecord();
				record.setDeleteFlag(VerificationEnum.DELETE_YES.getCode());
				record.setDeleteBy(loginUserInfo.getUserName());
				record.setDeleteAt(DateUtil.format(new Date()));
				Example examp = new Example(MeasureRecord.class);
				Example.Criteria crit = examp.createCriteria();
				crit.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
				crit.andEqualTo("proofDeviceId", proofDeviceId);
				crit.andEqualTo("bach", bach);
				crit.andEqualTo("terminal", VerificationEnum.SOURCE_WEB.getCode());
				measureRecordMapper.updateByExampleSelective(record, examp);

				//最近上报时间
				String reportTime = addRecord(addMeasureRecordDTO, proofDeviceId, bach, loginUserInfo.getUserName());
				//新增防错措施记录
				/*if (!CollectionUtils.isEmpty(addMeasureRecordDTO)) {
					reportTime = addMeasureRecordDTO.get(0).getReportTime();//最近上报时间赋予初值

					for (MeasureRecordDTO measureRecordDTO : addMeasureRecordDTO) {
						MeasureRecord measureRecord = new MeasureRecord();
						measureRecord.setId(OidUtil.newId());
						measureRecord.setProofDeviceId(proofDeviceId);
						measureRecord.setFaultPerformance(measureRecordDTO.getFaultPerformance());
						measureRecord.setEmergencyResponse(measureRecordDTO.getEmergencyResponse());
						measureRecord.setVerificationItem(measureRecordDTO.getVerificationItem());
						measureRecord.setVerificationContent(measureRecordDTO.getVerificationContent());

						//新增上报人员防错关联数据
						if (!StringUtils.isEmpty(measureRecordDTO.getReportPersons())) {
							String objId = OidUtil.newId();
							measureRecord.setReporterPersonUserObjId(objId);
							measureRecord.setReportTime(measureRecordDTO.getReportTime());
							addPersonProofLink(measureRecordDTO.getReportPersons().split(","), objId);
						}
						//新增修复人员防错关联数据
						if (!StringUtils.isEmpty(measureRecordDTO.getFixPersons())) {
							String objId = OidUtil.newId();
							measureRecord.setFixerPersonUserObjId(objId);
							measureRecord.setFixTime(measureRecordDTO.getFixTime());
							addPersonProofLink(measureRecordDTO.getFixPersons().split(","), objId);
						}

						measureRecord.setBach(bach);
						measureRecord.setIsQualified((byte) VerificationEnum.QUALIFIED_NO.getCode());
						measureRecord.setCreateAt(DateUtil.format(new Date()));
						measureRecord.setCreateBy("创建人");
						measureRecord.setDataVersion(1);
						measureRecord.setDeleteFlag(VerificationEnum.DELETE_NO.getCode());
						measureRecord.setTerminal((byte) VerificationEnum.SOURCE_WEB.getCode());
						measureRecordMapper.insertSelective(measureRecord);

						//比较最近上报时间大小
						if (reportTime != null && measureRecordDTO.getReportTime() != null) {
							int flagTime = DateCompareUtil.compareDate(reportTime, measureRecordDTO.getReportTime());
							if (flagTime < 0) reportTime = measureRecordDTO.getReportTime();
						}
					}
				}*/

				//修改防错装置最近上报时间(取最近上报时间最大的)
				updateReportTime(reportTime, proofDeviceId, null);
				/*if (reportTime != null) {
					ProofDevice proofDevice = new ProofDevice();
					proofDevice.setId(proofDeviceId);
					proofDevice.setReportTime(reportTime);
					proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);
				}*/

				//将防错装置改为运行中
				if (!flag) {
					ProofDevice proofDevice = new ProofDevice();
					proofDevice.setId(proofDeviceId);
					proofDevice.setStatus((byte) VerificationEnum.TempStates_ONGOING.getCode());
					proofDevice.setUpdateAt(DateUtil.format(new Date()));
					proofDevice.setUpdateBy(loginUserInfo.getUserName());
					proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);
				}

			}
		}
		return ResultStatus.SUCCESS;
	}

	/**
	 * 新增防错措施记录和人员关联数据
	 * @param addMeasureRecordDTO	需要新增的放错措施记录
	 * @param proofDeviceId					防错装置id
	 * @param bach									批次号
	 * @return											最近上报时间
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String addRecord(List<MeasureRecordDTO> addMeasureRecordDTO, String proofDeviceId, Integer bach, String userName) {
		if (!CollectionUtils.isEmpty(addMeasureRecordDTO)) {
			String reportTime = addMeasureRecordDTO.get(0).getReportTime();//最近上报时间赋予初值

			for (MeasureRecordDTO measureRecordDTO : addMeasureRecordDTO) {
				MeasureRecord measureRecord = new MeasureRecord();
				measureRecord.setId(OidUtil.newId());
				measureRecord.setProofDeviceId(proofDeviceId);
				measureRecord.setFaultPerformance(measureRecordDTO.getFaultPerformance());
				measureRecord.setEmergencyResponse(measureRecordDTO.getEmergencyResponse());
				measureRecord.setVerificationItem(measureRecordDTO.getVerificationItem());
				measureRecord.setVerificationContent(measureRecordDTO.getVerificationContent());

				//新增上报人员防错关联数据
				if (!StringUtils.isEmpty(measureRecordDTO.getReportPersons())) {
					String objId = OidUtil.newId();
					measureRecord.setReporterPersonUserObjId(objId);
					measureRecord.setReportTime(measureRecordDTO.getReportTime());
					addPersonProofLink(measureRecordDTO.getReportPersons().split(","), objId, userName);
				}
				//新增修复人员防错关联数据
				if (!StringUtils.isEmpty(measureRecordDTO.getFixPersons())) {
					String objId = OidUtil.newId();
					measureRecord.setFixerPersonUserObjId(objId);
					measureRecord.setFixTime(measureRecordDTO.getFixTime());
					addPersonProofLink(measureRecordDTO.getFixPersons().split(","), objId, userName);
				}

				measureRecord.setBach(bach);
				measureRecord.setIsQualified((byte) VerificationEnum.QUALIFIED_NO.getCode());
				measureRecord.setCreateAt(DateUtil.format(new Date()));
				measureRecord.setCreateBy(userName);
				measureRecord.setDataVersion(1);
				measureRecord.setDeleteFlag(VerificationEnum.DELETE_NO.getCode());
				measureRecord.setTerminal((byte) VerificationEnum.SOURCE_WEB.getCode());
				measureRecordMapper.insertSelective(measureRecord);

				//比较最近上报时间大小
				if (reportTime != null && measureRecordDTO.getReportTime() != null) {
					int flagTime = DateCompareUtil.compareDate(reportTime, measureRecordDTO.getReportTime());
					if (flagTime < 0) reportTime = measureRecordDTO.getReportTime();
				}
			}
			return reportTime;
		}
		return null;
	}

	/**
	 * 修改最近上报时间和状态
	 * @param reportTime		最近上报时间
	 * @param proofDeviceId	防错装置id
	 * @param status					防错装置状态
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateReportTime(String reportTime, String proofDeviceId, Byte status) {
		if (reportTime != null) {
			ProofDevice proofDevice = new ProofDevice();
			proofDevice.setId(proofDeviceId);
			proofDevice.setReportTime(reportTime);
			if (status != null)
				proofDevice.setStatus(status);
			proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);
		}
	}

	/**
	 * 删除防错措施记录
	 * @param id 防错措施id
	 * @return		结果状态
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultStatus delMeasureRecord(String id) {
		/*//查询数据是否存在
		MeasureRecord measureRecord = measureRecordMapper.selectByPrimaryKey(id);
		if (measureRecord == null) {
			return ResultStatus.DATA_NOT_FIND;
		}
		//查询是否终端记录
		if (measureRecord.getTerminal() == VerificationEnum.SOURCE_TERMINAL.getCode()) {
			return ResultStatus.TERMINAL_RECORD;
		}

		//逻辑删除防错措施记录表
		MeasureRecord mr = new MeasureRecord();
		mr.setId(measureRecord.getId());
		mr.setDeleteFlag(VerificationEnum.DELETE_YES.getCode());
		mr.setDeleteAt(DateUtil.format(new Date()));
		mr.setDeleteBy("创建人");
		measureRecordMapper.updateByPrimaryKeySelective(mr);

		//逻辑删除人员防错措施关联表
		PersonProofLink personProofLink = new PersonProofLink();
		personProofLink.setDeleteBy("创建人");
		personProofLink.setDeleteAt(DateUtil.format(new Date()));
		personProofLink.setDeleteFlag(VerificationEnum.DELETE_YES.getCode());
		//逻辑删除上报人关联数据
		if (!StringUtils.isEmpty(measureRecord.getReporterPersonUserObjId())) {
			Example example = new Example(PersonProofLink.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("objectId", measureRecord.getReporterPersonUserObjId());
			personProofLinkMapper.updateByExampleSelective(personProofLink, example);

			//TODO 修改最近上报时间

		}
		//逻辑删除修复人关联数据
		if (!StringUtils.isEmpty(measureRecord.getFixerPersonUserObjId())) {
			Example e = new Example(PersonProofLink.class);
			Example.Criteria c = e.createCriteria();
			c.andEqualTo("objectId", measureRecord.getFixerPersonUserObjId());
			personProofLinkMapper.updateByExampleSelective(personProofLink, e);
		}*/
		return ResultStatus.SUCCESS;
	}

	/**
	 * 查询防错措施记录表
	 * @param proofDeviceId		防错装置id
	 * @param status 					当前状态
	 * @param page						页码和每页显示数
	 * @return								防错措施列表
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MeasureRecordDTO> queryMeasureRecord(String proofDeviceId, Integer status, PageBean page) {
		//PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//如果防错装置是正常状态则没有防错记录
		if (status == VerificationEnum.TempStates_ONGOING.getCode()) {
			return null;
		}

		List<MeasureRecordDTO> measureRecordDTOList = measureRecordMapper.queryMeasureRecordList(proofDeviceId);
		if (!CollectionUtils.isEmpty(measureRecordDTOList)) {
			for (MeasureRecordDTO measureRecordDTO : measureRecordDTOList) {
				if (measureRecordDTO.getReporterPersonUserObjId() != null) {
					List<Person> reportPersonList = personProofLinkMapper.queryPersonListJoinLink(measureRecordDTO.getReporterPersonUserObjId());
					if (!CollectionUtils.isEmpty(reportPersonList)) {
						StringBuffer reportSB =new StringBuffer(reportPersonList.size());
						for (Person person : reportPersonList) {
							reportSB.append(person.getName());
							reportSB.append(",");
						}
						measureRecordDTO.setReportPersons(reportSB.substring(0, reportSB.length()-1));
						measureRecordDTO.setReportPersonList(reportPersonList);
					}
				}
				if (measureRecordDTO.getFixerPersonUserObjId() != null) {
					List<Person> fixPersonList = personProofLinkMapper.queryPersonListJoinLink(measureRecordDTO.getFixerPersonUserObjId());
					if (!CollectionUtils.isEmpty(fixPersonList)) {
						StringBuffer fixSB =new StringBuffer(fixPersonList.size());
						for (Person person : fixPersonList) {
							fixSB.append(person.getName());
							fixSB.append(",");
						}
						measureRecordDTO.setFixPersons(fixSB.substring(0, fixSB.length()-1));
						measureRecordDTO.setFixPersonList(fixPersonList);
					}
				}
			}
		}
		return measureRecordDTOList;
	}

	/**
	 * 根据批次号和防错装置id查询防错措施记录
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MeasureRecord> findRecordByProofDeviceIdWithBach(String id) {
		Integer bach = measureRecordMapper.queryMaxBach(id);
		List<MeasureRecord> list = measureRecordMapper.findRecordByProofDeviceIdWithBach(id, bach);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultStatus repairReport(String id, String rfidCardNo) {
		//根据卡号查询人员表
		Example example = new Example(Person.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("rfidCardNo", rfidCardNo);
		criteria.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
		Person person = personMapper.selectOneByExample(example);
		if (person == null)
			return ResultStatus.QUERY_PERSON_FAIL;
		String objId = OidUtil.newId();//引用对象id

		//插入人员防错关联表
		PersonProofLink personProofLink = new PersonProofLink();
		personProofLink.setId(OidUtil.newId());
		personProofLink.setObjectId(objId);
		personProofLink.setPersonId(person.getId());
		personProofLink.setCreateAt(DateUtil.format(new Date()));
		personProofLink.setCreateBy(person.getName());
		personProofLink.setDataVersion(1);
		personProofLink.setDeleteFlag(VerificationEnum.DELETE_NO.getCode());
		personProofLinkMapper.insertSelective(personProofLink);

		//修改防错措施记录表(添加修复人)
		Integer bach = measureRecordMapper.queryMaxBach(id);
		MeasureRecord measureRecord = new MeasureRecord();
		measureRecord.setFixerPersonUserObjId(objId);
		measureRecord.setFixTime(DateUtil.format(new Date()));
		measureRecord.setUpdateBy(person.getName());
		measureRecord.setUpdateAt(DateUtil.format(new Date()));
		measureRecord.setProofDeviceId(id);
		measureRecord.setBach(bach);
		measureRecordMapper.updateMeasureRecord(measureRecord);

		//修改防错装置(设置为运行中)
		ProofDevice proofDevice = new ProofDevice();
		proofDevice.setId(id);
		proofDevice.setStatus((byte) VerificationEnum.TempStates_ONGOING.getCode());
		proofDevice.setUpdateBy(person.getName());
		proofDevice.setUpdateAt(DateUtil.format(new Date()));
		proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);

		return ResultStatus.SUCCESS;
	}

	/**
	 * 故障上报
	 * @param id
	 * @param status
	 * @param rfidCardNo
	 * @param measureRecordDTOs
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultStatus postEquipmentFaults(String id, Integer status, String rfidCardNo, List<MeasureRecordDTO> measureRecordDTOs) {
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(status) || StringUtils.isEmpty(rfidCardNo))
			return ResultStatus.LACK_PARAM;

		//根据卡号查询人员表
		Example example = new Example(Person.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("rfidCardNo", rfidCardNo);
		criteria.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
		Person person = personMapper.selectOneByExample(example);
		if (person == null)
			return ResultStatus.QUERY_PERSON_FAIL;

		if (status == VerificationEnum.TempStates_ONGOING.getCode()) {
			String objId = OidUtil.newId();//引用对象id

			//插入人员防错关联表
			PersonProofLink personProofLink = new PersonProofLink();
			personProofLink.setId(OidUtil.newId());
			personProofLink.setObjectId(objId);
			personProofLink.setPersonId(person.getId());
			personProofLink.setCreateAt(DateUtil.format(new Date()));
			personProofLink.setCreateBy(person.getName());
			personProofLink.setDataVersion(1);
			personProofLink.setDeleteFlag(VerificationEnum.DELETE_NO.getCode());
			personProofLinkMapper.insertSelective(personProofLink);

			//对防错措施记录进行批次号查询查出最大批次号
			Integer bach = null;//批次号
			Integer maxBach = measureRecordMapper.queryMaxBach(id);
			//如果没查到则自己赋值一个初始批次
			if (maxBach == null) {
				bach = 1801;
			} else {
				bach = maxBach+1;
			}

			//插入防错措施记录表
			for (MeasureRecordDTO measureRecordDTO : measureRecordDTOs) {
				measureRecordDTO.setDataVersion(1);
				measureRecordDTO.setProofDeviceId(id);
				measureRecordDTO.setBach(bach);
				measureRecordDTO.setId(OidUtil.newId());
				measureRecordDTO.setReporterPersonUserObjId(objId);
				measureRecordDTO.setReportTime(DateUtil.format(new Date()));
				measureRecordDTO.setTerminal((byte) VerificationEnum.SOURCE_TERMINAL.getCode());
				measureRecordDTO.setCreateAt(DateUtil.format(new Date()));
				measureRecordDTO.setCreateBy(person.getName());
			}
			measureRecordMapper.insertBach(measureRecordDTOs);

			//修改最近上报时间和状态
			updateReportTime(DateUtil.format(new Date()), id, (byte) VerificationEnum.TempStates_LOSE_EFFICACY.getCode());
		} else {
			Integer maxBach = measureRecordMapper.queryMaxBach(id);
			//修改防错措施记录
			for (MeasureRecordDTO measureRecordDTO : measureRecordDTOs) {
				measureRecordDTO.setUpdateBy(person.getName());
				measureRecordDTO.setUpdateAt(DateUtil.format(new Date()));
				measureRecordDTO.setProofDeviceId(id);
				measureRecordDTO.setBach(maxBach);
			}
			measureRecordMapper.updateBach(measureRecordDTOs);
		}
		return ResultStatus.SUCCESS;
	}

	/**
	 * 根据id查询人员是否存在
	 * @param ids		人员id
	 * @return			人员id是否存在
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Boolean verificationPerson(List<String> ids) {
		boolean flag = true;
		for (String id : ids) {
			Example example = new Example(Person.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
			criteria.andEqualTo("id", id);
			Person person = personMapper.selectOneByExample(example);
			if (person == null) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 新增人员防错措施关联表
	 * @param ids			人员id数组
	 * @param objId		引用对象id
	 * @return				受影响数
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int addPersonProofLink(String[] ids, String objId, String userName) {
		List<PersonProofLink> list = new LinkedList<>();
		for (String id : ids) {
			PersonProofLink personProofLink = new PersonProofLink();
			personProofLink.setId(OidUtil.newId());
			personProofLink.setDataVersion(1);
			personProofLink.setCreateAt(DateUtil.format(new Date()));
			personProofLink.setCreateBy(userName);
			personProofLink.setPersonId(id);
			personProofLink.setObjectId(objId);
			list.add(personProofLink);
		}
		return personProofLinkMapper.insertBatch(list);
	}

}
