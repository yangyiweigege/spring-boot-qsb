package com.realtech.pandora.domain.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * 接受前端传来的分页数据
 * Created by zhuqq
 */

public class PageBean<T> {

    //结果集
    private List<T> list;

    //总记录数
    private long total;

    //页码
    private Integer pageNum;

    //页数
    private Integer pageSize;

    //总页数
    private Integer pages;

    //当前页数量
    @JsonInclude(Include.NON_NULL)
    private Integer size;

    public PageBean() {}

    public PageBean(List<T> list){
        if (list instanceof Page){
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
        }
    }
    
    public void setPageBean(Page<T> page) {
    	 this.pageNum = page.getPageNum();
         this.pageSize = page.getPageSize();
         this.total = page.getTotal();
         this.pages = page.getPages();
         this.size = page.size();
    }

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
		//算好总数
		this.pages = (int) ((total + pageSize - 1) / pageSize);
		if (pages < 1) {
			this.pages = 1;
		}
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
    
    

}
