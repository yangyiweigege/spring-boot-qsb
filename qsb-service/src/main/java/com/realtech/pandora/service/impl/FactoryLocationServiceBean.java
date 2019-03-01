package com.realtech.pandora.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.realtech.pandora.common.enums.VerificationEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.realtech.pandora.common.tree.FontTree;
import com.realtech.pandora.common.tree.Tree;
import com.realtech.pandora.common.utils.JVMCache;
import com.realtech.pandora.dao.FactoryLocationMapper;
import com.realtech.pandora.domain.FactoryLocation;
import com.realtech.pandora.service.FactoryLocationService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

@Service
@Transactional
public class FactoryLocationServiceBean implements FactoryLocationService {
	
	@Resource
	private FactoryLocationMapper factoryLocationMapper;
	/*//缓存返回给前端的结果
	private FontTree fontTreeCache;*/

	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public FontTree generateLocationTree() {
		if (JVMCache.fontTreeCache != null) {
			return JVMCache.fontTreeCache;
		}
		Tree<FactoryLocation> tree = new Tree<FactoryLocation>();
		//先查询第一级树(父节点)
		Example example = new Example(FactoryLocation.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("level", 0);
		criteria.andEqualTo("deleteFlag",VerificationEnum.DELETE_NO.getCode());
		/*if (!StringUtil.isEmpty(loginUserInfo.getFactoryId())) {
			criteria.andEqualTo("factoryId", loginUserInfo.getFactoryId());
		}*/

		List<FactoryLocation> parentList = factoryLocationMapper.selectByExample(example);
		for (FactoryLocation item : parentList) {
			Tree<FactoryLocation> node = new Tree<FactoryLocation>();
			node.setParent(item);
			tree.getChild().add(node);//添加为子节点
			//此处开始递归多层节点
			generateManyLevel(node);
		}
		FontTree fontTree = new FontTree();
		fontTree.setTitle("根节点");
		fontTree.setExpand(true);
		fontTree.setId(null);
		convertTree(tree, fontTree);
		JVMCache.fontTreeCache = fontTree;//写入缓存
		return JVMCache.fontTreeCache;
	}
	
	/**
	 * 递归形成多级节点
	 */
	private void generateManyLevel(Tree<FactoryLocation> node) {
		Example example = new Example(FactoryLocation.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("parentLocationId", node.getParent().getId());
		criteria.andNotEqualTo("factoryId", "???");//过滤垃圾数据
		criteria.andEqualTo("deleteFlag",VerificationEnum.DELETE_NO.getCode());
		List<FactoryLocation> sonList = factoryLocationMapper.selectByExample(example);
		for (FactoryLocation item : sonList) {
			Tree<FactoryLocation> sonNode = new Tree<FactoryLocation>();
			sonNode.setParent(item);
			node.getChild().add(sonNode);//将子节点，挂载到父节点
			//继续递归形成子节点的子节点
			generateManyLevel(sonNode);
		}
	}
	
	/**
	 * 将后端树转换为前端要求的格式
	 */
	private void convertTree(Tree<FactoryLocation> tree, FontTree fontTree) {
		for (Tree<FactoryLocation> son : tree.getChild()) {
			FontTree fontSon = new FontTree();
			fontSon.setId(son.getParent().getId());
			fontSon.setTitle(son.getParent().getName());
			fontSon.setExpand(true);
			fontSon.setType(son.getParent().getType().intValue());
			fontSon.setFactoryId(son.getParent().getFactoryId());
			fontTree.getChildren().add(fontSon);
			convertTree(son,fontSon);
		}
		
		if (fontTree.getChildren().size() == 0) {
			fontTree.setExpand(false);
		}
	}

}
