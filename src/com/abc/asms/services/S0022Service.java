package com.abc.asms.services;

import com.abc.asms.forms.S0022Form;

public class S0022Service { //売上詳細表示のサービス

	public S0022Form findSaleDetail(String saleId) {


		S0022Form form = new S0022Form(null, null, null, null, null, 0, 0, null);
		return form;
	}
}
