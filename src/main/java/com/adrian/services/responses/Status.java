package com.adrian.services.responses;

public class Status {
	private int code;
	private String text;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Status [code=" + code + ", text=" + text + "]";
	}
}