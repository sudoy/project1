package com.abc.asms.forms;

import java.util.Map;

public class S0024Form {

	private String saleId;
	private String saleDate;
	private String accountId;
	private String name;
	private String categoryId;
	private String categoryName;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String note;
	private String input;
	private String subtotal;
	private Map<Integer,String> categoryMap;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getInput() {
		return input;
	}

	public void setInput() {
		this.input = "saleId=" + getSaleId()
		+ "&saleDate=" + getSaleDate()
		+ "&accountId=" + getAccountId()
		+ "&categoryId=" + getCategoryId()
		+ "&tradeName=" + getTradeName()
		+ "&unitPrice=" + getUnitPrice()
		+ "&saleNumber=" + getSaleNumber()
		+ "&note=" + getNote()
		+ "&cancel=";
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal() {
		int st = Integer.valueOf(getUnitPrice()) * Integer.valueOf(getSaleNumber());
		this.subtotal = String.valueOf(st);
	}

	public Map<Integer,String> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<Integer,String> categoryMap) {
		this.categoryMap = categoryMap;
	}

}
