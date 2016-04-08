package com.bhz.educard;

public class ResultMessage {
	public final static int OP_SUCCESS = 0;
	public final static int OP_ERROR = 1;
	public final static int OP_WARNING = 2;

	private int resultType;
	private String message;

	public ResultMessage() {

	}

	public ResultMessage(int resultType,String message) {
		this.resultType = resultType;
		this.message = message;
	}

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
