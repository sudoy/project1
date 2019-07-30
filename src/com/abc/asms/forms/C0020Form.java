package com.abc.asms.forms;

import java.time.LocalDate;

public class C0020Form {


	private int saleId;
	private String saleDate;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String rate;
	private long subTotal;


	private LocalDate date;
	private LocalDate lastMonth;
	private long saleMonth;
	private long saleLastMonth;
	private double percent;
	private long total;

	private StringBuilder thisYearList;
	private StringBuilder lastYearList;
	private long maxSale;


	public C0020Form(int saleId, String saleDate, String categoryName, String tradeName, int unitPrice, int saleNumber,
			long subTotal,String rate) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.subTotal = subTotal;
		this.rate = rate;
	}

	public C0020Form(LocalDate date, LocalDate lastMonth, long saleMonth, long saleLastMonth, double percent, long total) {
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

	public long getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(long subTotal) {
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

	public long getSaleMonth() {
		return saleMonth;
	}

	public void setSaleMonth(long saleMonth) {
		this.saleMonth = saleMonth;
	}

	public long getSaleLastMonth() {
		return saleLastMonth;
	}

	public void setSaleLastMonth(long saleLastMonth) {
		this.saleLastMonth = saleLastMonth;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public StringBuilder getThisYearList() {
		return thisYearList;
	}

	public void setThisYearList(StringBuilder thisYearList) {
		this.thisYearList = thisYearList;
	}

	public StringBuilder getLastYearList() {
		return lastYearList;
	}

	public void setLastYearList(StringBuilder lastYearList) {
		this.lastYearList = lastYearList;
	}

	public long getMaxSale() {
		return maxSale;
	}

	public void setMaxSale(long maxSale) {
		this.maxSale = maxSale;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
}
