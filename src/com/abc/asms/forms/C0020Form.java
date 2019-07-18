package com.abc.asms.forms;

import java.time.LocalDate;

public class C0020Form {


	private int saleId;
	private String saleDate;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private int subTotal;


	private LocalDate date;
	private LocalDate lastMonth;
	private int saleMonth;
	private int saleLastMonth;
	private double percent;
	private int total;





	public C0020Form(int saleId, String saleDate, String categoryName, String tradeName, int unitPrice, int saleNumber,
			int subTotal) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.subTotal = subTotal;
	}

	public C0020Form(LocalDate date, LocalDate lastMonth, int saleMonth, int saleLastMonth, double percent, int total) {
		super();
		this.date = date;
		this.lastMonth = lastMonth;
		this.saleMonth = saleMonth;
		this.saleLastMonth = saleLastMonth;
		this.percent = percent;
		this.total = total;
	}

	public C0020Form() {

	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
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

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(LocalDate lastMonth) {
		this.lastMonth = lastMonth;
	}

	public int getSaleMonth() {
		return saleMonth;
	}

	public void setSaleMonth(int saleMonth) {
		this.saleMonth = saleMonth;
	}

	public int getSaleLastMonth() {
		return saleLastMonth;
	}

	public void setSaleLastMonth(int saleLastMonth) {
		this.saleLastMonth = saleLastMonth;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}




}
