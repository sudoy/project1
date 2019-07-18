package com.abc.asms.forms;

public class EntryAccountForm {

	private String name;
	private String mail;
	private String password;
	private String password2;
	private String salesAuthority;
	private String accountAuthority;

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
	public String getSalesAuthority() {
		return salesAuthority;
	}
	public void setSalesAuthority(String salesAuthority) {
		this.salesAuthority = salesAuthority;
	}
	public String getAccountAuthority() {
		return accountAuthority;
	}
	public void setAccountAuthority(String accountAuthority) {
		this.accountAuthority = accountAuthority;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
