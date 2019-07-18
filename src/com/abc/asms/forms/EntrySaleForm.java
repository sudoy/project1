package com.abc.asms.forms;

public class EntrySaleForm {
	private String saleDate;
	private String accountId;
	private String categoryId;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String note;
	private String subTotal;

	private String categoryName;
	private String activeFlg;
	private String name;

	private StringBuilder cancelData;

	public EntrySaleForm(String saleDate, String accountId, String categoryId, String tradeName, String unitPrice,
			String saleNumber, String note, String subTotal) {
		super();
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
		this.subTotal = subTotal;
	}


	public EntrySaleForm(String saleDate, String accountId, String categoryId, String tradeName, String unitPrice,
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

	public EntrySaleForm(String accountId, String name) {
		super();
		this.accountId = accountId;
		this.name = name;
	}


	public EntrySaleForm(String categoryId, String categoryName, String active_flg) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.activeFlg = active_flg;
	}

	public EntrySaleForm() {

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

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
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


	public StringBuilder getCancelData() {
		return cancelData;
	}


	public void setCancelData(StringBuilder cancelData) {
		this.cancelData = cancelData;
	}



}
