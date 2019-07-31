package com.abc.asms.forms;

import java.util.LinkedHashMap;

public class S0022Form { //売上詳細表示のフォーム

	private String saleId;
	private String saleDate;
	private String name;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private int rate;
	private String note;
	private String salesAuthority;
	private LinkedHashMap<Integer, String> histories;
	private String updateAt;
	private String updateBy;
	private String historyId;

	public S0022Form(String saleId, String saleDate, String name, String categoryName, String tradeName, int unitPrice,
			int saleNumber, int rate, String note) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.rate = rate;
		this.note = note;
	}

	public S0022Form(String saleId, String saleDate, String name, String categoryName, String tradeName, int unitPrice,
			int saleNumber, int rate, String note,String updateAt, String updateBy) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.rate = rate;
		this.note = note;
		this.updateAt = updateAt;
		this.updateBy = updateBy;
	}


	public S0022Form(String saleId, String saleDate, String name, String categoryName, String tradeName, int unitPrice,
			int saleNumber, int rate, String note, String updateAt, String updateBy, String historyId) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.rate = rate;
		this.note = note;
		this.updateAt = updateAt;
		this.updateBy = updateBy;
		this.historyId = historyId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getSalesAuthority() {
		return salesAuthority;
	}

	public void setSalesAuthority(String salesAuthority) {
		this.salesAuthority = salesAuthority;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}

	public LinkedHashMap<Integer, String> getHistories() {
		return histories;
	}

	public void setHistories(LinkedHashMap<Integer, String> histories) {
		this.histories = histories;
	}


	public String getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}


	public String getUpdateBy() {
		return updateBy;
	}


	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

}
