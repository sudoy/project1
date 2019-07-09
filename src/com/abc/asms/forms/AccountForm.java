package com.abc.asms.forms;

public class AccountForm {
	private int accountId;
	private String name;
	private int authority;
	public AccountForm(int accountId, String name, int authority) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.authority = authority;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
}
