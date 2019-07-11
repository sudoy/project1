package com.abc.asms.forms;

public class S0041Form {
	String account_id;
	String name;
	String mail;
	String authority;
	public S0041Form(String account_id, String name, String mail, String authority) {
		super();
		this.account_id = account_id;
		this.name = name;
		this.mail = mail;
		this.authority = authority;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}