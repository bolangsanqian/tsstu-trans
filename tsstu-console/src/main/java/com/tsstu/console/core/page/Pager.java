package com.tsstu.console.core.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageRowBounds;
import com.tsstu.common.util.CommonUtils;

public class Pager<T> {
	
	private int DEFAULT_PAGE_NUM = 1;
	private int DEFAULT_PAGE_SIZE = 10;
	
	// 查询页数
	private int pageNum = DEFAULT_PAGE_NUM;
	
	// 每页数量
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	// 分页信息
	private PageRowBounds rowBounds = null;
	
	// 总行数
	private long totalRow = 0;
	
	// 总页数
	private long totalPage = 0;
	
	private T model = null;
	
	// 查询条件
	private PageData pageData = null;
	
	// 查询结果列表
	private List<T> result = new ArrayList<T>();
	
	// 排序
	private String orderBy = "";
	
	// 排序方向
	private String sort_direction = "default";
	
    // 页面开始索引
    protected int startIndex = 0;
	
	/**
	 * 初始化
	 * @param request
	 */
	public void init(HttpServletRequest request) {
		pageData = new PageData(request);
		initPageBefore();
	}
	
	/**
	 * 初始化分页信息(查询前)
	 */
	public void initPageBefore() {
		// pageNum、pageSize、 rowBounds
		this.pageNum = pageData.getInt("pageNum") <= 0 ? DEFAULT_PAGE_NUM : pageData.getInt("pageNum");
		this.pageSize = pageData.getInt("pageSize") <= 0 ? DEFAULT_PAGE_SIZE : pageData.getInt("pageSize");
		//this.rowBounds = new PageRowBounds((this.pageNum - 1) * this.pageSize, this.pageSize);
		this.rowBounds = new PageRowBounds(this.pageNum, this.pageSize);
	}
	
	public void initExportQuery() {
		this.pageNum = 1;
		this.pageSize = 0;
		this.rowBounds = new PageRowBounds(0, 0);
	}
	
	/**
	 * 初始化分页信息(查询后)
	 */
	public void initPageAfter(List<T> list) {getModel();
		// totalRows、totalPage、result
		this.totalRow = this.rowBounds.getTotal();
		if (this.pageSize > 0) {
			this.totalPage = this.rowBounds.getTotal() % this.getPageSize() > 0 ? (this.rowBounds.getTotal() / this.getPageSize() + 1) : this.rowBounds.getTotal() / this.getPageSize();
		} else {
			this.pageSize = Integer.valueOf(this.totalRow + "");
			this.totalPage = 1;
		}
		this.startIndex = (this.pageNum - 1) * this.pageSize + 1;
		this.result = list;
	}
	
	public void putData(String key, Object value) {
		this.pageData.put(key, value);
	}
	
	public PageRowBounds getRowBounds() {
		return rowBounds;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Map<String, Object> getCondition() {
		return pageData;
	}

	public void setCondition(PageData condition) {
		this.pageData = condition;
	}

	public long getTotalRow() {
		return totalRow;
	}

	public void setTotalRows(long totalRow) {
		this.totalRow = totalRow;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public PageData getPageData() {
		return pageData;
	}

	public void setPageData(PageData pageData) {
		this.pageData = pageData;
	}

	public void setRowBounds(PageRowBounds rowBounds) {
		this.rowBounds = rowBounds;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
	}

	public String getSort_direction() {
		return sort_direction;
	}

	public void setSort_direction(String sort_direction) {
		this.sort_direction = sort_direction;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public String getString(String key) {
		if (CommonUtils.isNullOrEmpty(this.pageData.get(key))) {
			return null;
		}
		return this.pageData.get(key).toString();
	} 
	
	public Integer getInteger(String key) {
		if (CommonUtils.isNullOrEmpty(this.pageData.get(key))) {
			return null;
		}
		return Integer.valueOf(this.pageData.get(key).toString());
	}
	
	public int getInt(String key) {
		if (CommonUtils.isNullOrEmpty(this.pageData.get(key))) {
			return 0;
		}
		return Integer.valueOf(this.pageData.get(key).toString());
	}
	
	public boolean isNullOrEmpty(String key) {
		return CommonUtils.isNullOrEmpty(this.pageData.get(key));
	}
	
	public boolean isNullOrZero(String key) {
		if (CommonUtils.isNullOrEmpty(this.pageData.get(key)) || Integer.valueOf(this.getString(key)) == 0) {
			return true;
		}
		return false;
	}
	
	
}
