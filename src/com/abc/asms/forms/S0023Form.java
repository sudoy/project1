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
	private Map<Integer,String> accountMap;
	private Map<Integer,String> categoryMap;
	private int version;

	public S0023Form(String saleId, String saleDate, String accountId, String categoryId, String tradeName,
			String unitPrice, String saleNumber, String note) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}

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

	public Map<Integer,String> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Integer,String> accountMap) {
		this.accountMap = accountMap;
	}

	public Map<Integer,String> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<Integer,String> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
