package com.lunch4you.domain;


public class OrderResult {

	public static enum ResultCode {
		OK, NOT_AVAILABLE, SOLD_OUT;
	}

	private ResultCode resultCode;

	private Order order;

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}

