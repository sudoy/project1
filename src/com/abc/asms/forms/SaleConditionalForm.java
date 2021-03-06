package com.abc.asms.forms;

import java.util.List;

public class SaleConditionalForm {
	String[] date;
	String accountId;
	String[] categoryId;
	String tradeName;
	String note;

	String item;
	String sort;
	int page;
	List<Integer> pageList;

	public SaleConditionalForm(String[] date, String accountId, String[] categoryId, String tradeName, String note) {
		super();
		this.date = date;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.note = note;
	}

	public String[] getDate() {
		return date;
	}
	public void setDate(String[] date) {
		this.date = date;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String[] getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String[] categoryId) {
		this.categoryId = categoryId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}





}
