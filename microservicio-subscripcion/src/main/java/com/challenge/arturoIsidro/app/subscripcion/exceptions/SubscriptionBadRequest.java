package com.challenge.arturoIsidro.app.subscripcion.exceptions;

public class SubscriptionBadRequest extends Exception {

	private static final long serialVersionUID = -1819310116407617109L;

	private String codError;
	private String msgError;
		
	public SubscriptionBadRequest(String codError, String msgError) {
		this.codError = codError;
		this.msgError = msgError;
	}

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
	
}
