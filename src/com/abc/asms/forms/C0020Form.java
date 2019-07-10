package com.abc.asms.forms;

public class C0020Form {


	private int saleId;
	private String saleDate;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private int subtotal;

	public C0020Form(int saleId, String saleDate, String categoryName, String tradeName, int unitPrice, int saleNumber,
			int subtotal) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.subtotal = subtotal;
	}

	public C0020Form() {

	}


	public int getSaleId() {
		return saleId;
	}


	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}



	public C0020Form(String saleDate, String categoryName, String tradeName, int unitPrice, int saleNumber,
			int subtotal, int saleId) {
		super();
		this.saleDate = saleDate;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.subtotal = subtotal;
		this.saleId = saleId;
	}


	public String getSaleDate() {
		return saleDate;
	}


	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getTradeName() {
		return tradeName;
	}


	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}


	public int getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}


	public int getSaleNumber() {
		return saleNumber;
	}


	public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}


	public int getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}





}
