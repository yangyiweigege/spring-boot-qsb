package com.realtech.pandora.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回给前端的树
 * 
 * @author yangyiwei
 * @date 2018年7月30日
 * @time 上午9:25:59
 */
public class FontTree {

	private boolean expand;

	private String id;

	private String title;

	private Integer type;

	private String factoryId;

	private List<FontTree> children = new ArrayList<FontTree>();

	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FontTree> getChildren() {
		return children;
	}

	public void setChildren(List<FontTree> children) {
		this.children = children;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
}
