package com.abc.asms.forms;

import java.util.Map;

public class S0023Form {

	private String saleId;
	private String saleDate;
	private String accountId;
	private String categoryId;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String note;
	private Map<String,String> accountList;
	private Map<String,String> categoryList;

	public S0023Form() {
		super();
	}

	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Map<String, String> getAccountList() {
		return accountList;
	}
	public void setAccountList(Map<String, String> accountList) {
		this.accountList = accountList;
	}
	public Map<String, String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(Map<String, String> categoryList) {
		this.categoryList = categoryList;
	}

}
