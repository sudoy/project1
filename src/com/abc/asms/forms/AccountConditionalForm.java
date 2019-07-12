package com.abc.asms.forms;

public class AccountConditionalForm {
	String name;
	String mail;
	String accountAuthority;
	String salesAuthority;
	public AccountConditionalForm(String name, String mail, String accountAuthority, String salesAuthority) {
		super();
		this.name = name;
		this.mail = mail;
		this.accountAuthority = accountAuthority;
		this.salesAuthority = salesAuthority;
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
	public String getAccountAuthority() {
		return accountAuthority;
	}
	public void setAccountAuthority(String accountAuthority) {
		this.accountAuthority = accountAuthority;
	}
	public String getSalesAuthority() {
		return salesAuthority;
	}
	public void setSalesAuthority(String salesAuthority) {
		this.salesAuthority = salesAuthority;
	}

}
