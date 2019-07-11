package com.abc.asms.forms;

public class AccountConditionalForm {
	String name;
	String mail;
	String authority;
	public AccountConditionalForm(String name, String mail, String authority) {
		super();
		this.name = name;
		this.mail = mail;
		this.authority = authority;
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
