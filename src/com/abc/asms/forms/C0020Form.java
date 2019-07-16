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

	private int monthval;
	private int yearval;
	private int lastmonthval;
	private double salemonth;
	private double salelastmonth;
	private double percent;
	private int total;
	private LocalDate date;


	public C0020Form(LocalDate date, int lastmonthval, double salemonth, double salelastmonth, double percent,
			int total) {
		super();
		this.date = date;
		this.lastmonthval = lastmonthval;
		this.salemonth = salemonth;
		this.salelastmonth = salelastmonth;
		this.percent = percent;
		this.total = total;
	}

	public C0020Form(int monthval, int yearval, int lastmonthval, double salemonth, double salelastmonth,
			double percent, int total) {
		super();
		this.monthval = monthval;
		this.yearval = yearval;
		this.lastmonthval = lastmonthval;
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

	public int getMonthval() {
		return monthval;
	}

	public void setMonthval(int monthval) {
		this.monthval = monthval;
	}

	public int getLastmonthval() {
		return lastmonthval;
	}

	public void setLastmonthval(int lastmonthval) {
		this.lastmonthval = lastmonthval;
	}

	public double getSalemonth() {
		return salemonth;
	}

	public void setSalemonth(double salemonth) {
		this.salemonth = salemonth;
	}

	public double getSalelastmonth() {
		return salelastmonth;
	}

	public void setSalelastmonth(double salelastmonth) {
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

	public int getYearval() {
		return yearval;
	}

	public void setYearval(int yearval) {
		this.yearval = yearval;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}



}
