package com.abc.asms.forms;

import java.time.LocalDate;

public class C0020Form {


	private int saleId;
	private String saleDate;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private int subtotal;


	private LocalDate date;
	private LocalDate lastmonth;
	private int salemonth;
	private int salelastmonth;
	private double percent;
	private int total;



	public C0020Form(LocalDate date, LocalDate lastmonth, int salemonth, int salelastmonth, double percent,
			int total) {
		super();
		this.date = date;
		this.lastmonth = lastmonth;
		this.salemonth = salemonth;
		this.salelastmonth = salelastmonth;
		this.percent = percent;
		this.total = total;
	}

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


	public int getSalemonth() {
		return salemonth;
	}

	public void setSalemonth(int salemonth) {
		this.salemonth = salemonth;
	}

	public int getSalelastmonth() {
		return salelastmonth;
	}

	public void setSalelastmonth(int salelastmonth) {
		this.salelastmonth = salelastmonth;
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


	public int getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getLastmonth() {
		return lastmonth;
	}

	public void setLastmonth(LocalDate lastmonth) {
		this.lastmonth = lastmonth;
	}





}
