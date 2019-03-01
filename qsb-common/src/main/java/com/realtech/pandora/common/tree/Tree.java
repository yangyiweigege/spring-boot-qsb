package com.realtech.pandora.common.tree;

import java.util.ArrayList;
import java.util.List;

//import com.alibaba.fastjson.JSONObject;
/**
 * <pre>
 * 功       能:树节点 
 * 涉及版本: V3.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2018年3月9日 下午3:35:20
 * Q    Q: 2873824885
 * </pre>
 */
public class Tree<T> {
	/**
	 * 父节点
	 */
	private T parent;

	/**
	 * 子节点
	 */
	private List<Tree<T>> child = new ArrayList<Tree<T>>();

	public Tree() {

	}

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public List<Tree<T>> getChild() {
		return child;
	}

	public void setChild(List<Tree<T>> child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "Tree [parent=" + parent + ", child=" + child + "]";
	}

}
