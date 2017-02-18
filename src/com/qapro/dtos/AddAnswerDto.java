package com.qapro.dtos;

import com.qapro.enums.AddAnswerResult;

public class AddAnswerDto {

	private AddAnswerResult status;
	private String text;

	public AddAnswerDto() {
	}

	public AddAnswerDto(AddAnswerResult status, String text) {
		super();
		this.status = status;
		this.text = text;
	}

	public AddAnswerResult getStatus() {
		return status;
	}

	public void setStatus(AddAnswerResult status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
