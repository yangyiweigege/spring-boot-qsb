package com.realtech.pandora.controller;
import com.realtech.pandora.common.enums.VerificationEnum;
import com.realtech.pandora.common.utils.JVMCache;
import com.realtech.pandora.common.utils.LoginUserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.annotation.ValidatePage;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.tree.FontTree;
import com.realtech.pandora.domain.DTO.EquipmentDTO;
import com.realtech.pandora.domain.page.PageBean;
import com.realtech.pandora.service.EquipmentService;
import com.realtech.pandora.service.FactoryLocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 工厂地点业务逻辑层
 * 
 * @author yangyiwei
 * @date 2018年7月19日
 * @time 下午3:51:52
 */
@RestController
@RequestMapping("/factory/location")
@Api(value = "factory-location-controller", description = "工厂位置控制层")
public class FactoryLocationController {

	@Autowired
	private FactoryLocationService factoryLocationService;
	@Autowired
	private EquipmentService equipmentService;
	

	@RequestMapping("/generate/map")
	@ApiOperation(value = "形成工厂地图树")
	public ResponseResult<FontTree> generateMap(String _pandora_p_acc_) {
		synchronized (this) {
			FontTree tree = factoryLocationService.generateLocationTree();
			LoginUserInfo loginUserInfo = JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_);
			if (!loginUserInfo.getFactoryId().equals(VerificationEnum.FACTORY_ID.getMsg())) {
				FontTree treeForUser = new FontTree();
				BeanUtils.copyProperties(tree, treeForUser);
				for (FontTree t : treeForUser.getChildren()) {
					if (t.getFactoryId().equals(loginUserInfo.getFactoryId())) {
						List<FontTree> treeList = new ArrayList<>();
						treeList.add(t);
						treeForUser.setChildren(treeList);
					}
				}
				return new ResponseResult<FontTree>(ResultStatus.SUCCESS, treeForUser);
			}
			return new ResponseResult<FontTree>(ResultStatus.SUCCESS, tree);
		}
	}

	@RequestMapping("/bind/device")
	@ApiOperation(value = "获取地点下所有设备及其防错装置情况")
	@ApiImplicitParams(value = {@ApiImplicitParam(name = "id", value = "地点id", required = true, dataType = "String")})
	@ValidatePage
	@ValidateAttribute(attributes = {"id"})
	public ResponseResult<PageBean<EquipmentDTO>> getBindDeviceAndMeasure(String id, PageBean<EquipmentDTO> pageBean) {
		return new ResponseResult<PageBean<EquipmentDTO>>(ResultStatus.SUCCESS, equipmentService.getDeviceAndProof(id, pageBean));
	}

}
