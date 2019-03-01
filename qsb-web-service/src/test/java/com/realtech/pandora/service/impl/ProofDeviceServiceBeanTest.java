package com.realtech.pandora.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.realtech.pandora.domain.MeasureTemplate;
import com.realtech.pandora.service.MeasureTemplateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.realtech.pandora.QsbApplicationTests;
import com.realtech.pandora.domain.page.PageBean;

/**
 * Created by zhuqq
 */
public class ProofDeviceServiceBeanTest extends QsbApplicationTests {

    @Autowired
    ProofDeviceServiceBean proofDeviceServiceBean;

    @Autowired
    MeasureTemplateService measureTemplateService;

    @Test
    public void queryErrorRecord() {
      //  List<MeasureTemplate> list = measureTemplateService.findTemplateById("JRZN7TPTBCSLL03U7SFK");
       // System.out.println(list);
    }
    
   /* public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		int pageSize = 5;
		int pageNum = 2;
		System.out.println(list.subList( (pageNum - 1) * pageSize, pageNum * pageSize).toString());
	}*/
}
