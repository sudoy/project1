package com.abc.asms.forms;

public class S0011Form {
	private String saleDate;
	private String accountId;
	private String categoryId;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String note;
	private String subtotal;

	private String categoryName;
	private String activeFlg;
	private String name;

	private StringBuilder canceldata;

	public S0011Form(String saleDate, String accountId, String categoryId, String tradeName, String unitPrice,
			String saleNumber, String note, String subtotal) {
		super();
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
		this.subtotal = subtotal;
	}


	public S0011Form(String saleDate, String accountId, String categoryId, String tradeName, String unitPrice,
			String saleNumber, String note) {
		super();
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}

	public S0011Form(String accountId, String name) {
		super();
		this.accountId = accountId;
		this.name = name;
	}


	public S0011Form(String categoryId, String categoryName, String active_flg) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.activeFlg = active_flg;
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
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(String active_flg) {
		this.activeFlg = active_flg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public StringBuilder getCanceldata() {
		return canceldata;
	}


	public void setCanceldata(StringBuilder canceldata) {
		this.canceldata = canceldata;
	}
}
