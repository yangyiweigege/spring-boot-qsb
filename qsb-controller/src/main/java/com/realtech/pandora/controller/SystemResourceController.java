package com.realtech.pandora.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.FileUtils;
import com.realtech.pandora.domain.SystemResource;
import com.realtech.pandora.service.SystemResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/system/resource/")
@Api(value = "system-resource-controller", description = "系统资源控制层")
@Slf4j
public class SystemResourceController {

	@Value("${upload.localtion.windows}")
	private String path;
	@Resource
	private SystemResourceService systemResourceService;
	
	/*@RequestMapping("/hello")
	@CheckPageBean(currentPage = "pageNum", pageSize = "pageSize")
	public String sayHello(HttpServletRequest request) {
		log.info("实际类型是:{},当前页码:{},分页尺寸{}", request.getClass().getName(), request.getParameter("pageNum"), request.getParameter("pageSize"));
		return request.getParameter("name");
	}*/

	/**
	 * 上传图片
	 * 
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "上传图片")
	@PostMapping(value = "/upload")
	public ResponseResult<String> uploadImage(MultipartFile file) {
		log.info("有用户尝试上传图片资源......");
		if (file == null) {
			return new ResponseResult<>(ResultStatus.FILE_UPLOAD_ERROR);
		}
		//判断是不是图片
		int suffixInde = file.getOriginalFilename().lastIndexOf(".");
		String suffix = file.getOriginalFilename().substring(suffixInde + 1);
		log.info("得到的文件后缀格式 : " + suffix);
		if (!suffix.equals("png") && !suffix.equals("jpeg") && !suffix.equals("jpg")) {
			return new ResponseResult<>(ResultStatus.FILE_UPLOAD_TYPE_ERROR);
		}
		String imgName = null;
		try {
			imgName = FileUtils.saveImg(file, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseResult<String>(ResultStatus.SUCCESS, imgName);
	}

	/**
	 * 查看图片通过对象引用id
	 * 
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "下载图片")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query")
	@GetMapping(value = "/download")
	@ValidateAttribute(attributes = {"id"})
	public void uploadImage(String id, HttpServletResponse response) {
		// 根据id,查询对应的图片资源
		SystemResource resource= systemResourceService.findResourceById(id);
		String resourcePath = "";
		if (resource != null) {
			resourcePath = resource.getAccessUrl();
		}
		File file = new File(resourcePath);
		if (!file.exists()) { // 如果不存在，返回
			return; // 或者整个默认图标
		}
		try {
			@SuppressWarnings("resource")
			InputStream inputStream = new FileInputStream(file);
			byte buffer[] = new byte[inputStream.available()];
			inputStream.read(buffer);
			int suffixInde = resourcePath.lastIndexOf(".");
			log.info("得到的图片后缀 : " + resourcePath.substring(suffixInde + 1));
			if (resourcePath.substring(suffixInde + 1).equals("png")) {
				response.setContentType("image/png");
			} else if (resourcePath.substring(suffixInde + 1).equals("jpeg")) {
				response.setContentType("image/jpeg");
			} else if (resourcePath.substring(suffixInde + 1).equals("jpeg")) {
				response.setContentType("image/jpg");
			} else { //预留一下
				;
			}
			OutputStream stream = response.getOutputStream();
			stream.write(buffer);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查看图片通过图片名
	 * 
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "查看图片")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query")
	@GetMapping(value = "/show")
	@ValidateAttribute(attributes = {"imageName"})
	public void showImage(String imageName, HttpServletResponse response) {
		// 根据id,查询对应的图片资源
		File file = new File(path + imageName);
		if (!file.exists()) { // 如果不存在，返回
			return; // 或者整个默认图标
		}
		try {
			@SuppressWarnings("resource")
			InputStream inputStream = new FileInputStream(file);
			byte buffer[] = new byte[inputStream.available()];
			inputStream.read(buffer);
			int suffixInde = imageName.lastIndexOf(".");
			log.info("用户请求图片展示.图片名称:{}....得到的图片后缀 :{} ",imageName, imageName.substring(suffixInde + 1));
			if (imageName.substring(suffixInde + 1).equals("png")) {
				response.setContentType("image/png");
			} else if (imageName.substring(suffixInde + 1).equals("jpeg")) {
				response.setContentType("image/jpeg");
			} else if (imageName.substring(suffixInde + 1).equals("jpeg")) {
				response.setContentType("image/jpg");
			} else { //预留一下
				;
			}
			OutputStream stream = response.getOutputStream();
			stream.write(buffer);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
