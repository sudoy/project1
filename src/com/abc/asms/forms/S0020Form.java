package com.abc.asms.forms;

import java.util.Map;

public class S0020Form {
	String saleDate1;
	String saleDate2;
	String accountId;
	String categoryId[];
	String tradeName;
	String note;
	Map<Integer, String> accountMap;
	Map<Integer, String> categoryMap;
	public S0020Form(String saleDate1, String saleDate2, String accountId, String[] categoryId, String tradeName,
			String note, Map<Integer, String> accountMap, Map<Integer, String> categoryMap) {
		super();
		this.saleDate1 = saleDate1;
		this.saleDate2 = saleDate2;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.note = note;
		this.accountMap = accountMap;
		this.categoryMap = categoryMap;
	}
	public String getSaleDate1() {
		return saleDate1;
	}
	public void setSaleDate1(String saleDate1) {
		this.saleDate1 = saleDate1;
	}
	public String getSaleDate2() {
		return saleDate2;
	}
	public void setSaleDate2(String saleDate2) {
		this.saleDate2 = saleDate2;
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
	public Map<Integer, String> getAccountMap() {
		return accountMap;
	}
	public void setAccountMap(Map<Integer, String> accountMap) {
		this.accountMap = accountMap;
	}
	public Map<Integer, String> getCategoryMap() {
		return categoryMap;
	}
	public void setCategoryMap(Map<Integer, String> categoryMap) {
		this.categoryMap = categoryMap;
	}

}