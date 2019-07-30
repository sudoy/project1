package com.abc.asms.forms;

public class AccountEditForm {

	private String accountId;
	private String name;
	private String mail;
	private String inputPass;
	private String inputPass2;
	private String salesAuthority;
	private String accountAuthority;
	private int version;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getInputPass() {
		return inputPass;
	}

	public void setInputPass(String inputPass) {
		this.inputPass = inputPass;
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

	public String getInputPass2() {
		return inputPass2;
	}

	public void setInputPass2(String inputPass2) {
		this.inputPass2 = inputPass2;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
