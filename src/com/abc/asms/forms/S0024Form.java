package com.abc.asms.forms;

public class S0024Form {

	private String saleId;
	private String saleDate;
	private String accountId;
	private String name;
	private String categoryId;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;
	private String input;

	public S0024Form() {
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

}
