package com.qapro.dtos;

import com.qapro.enums.DeleteQuestionResult;

public class DeleteQuestionDto {
	
	private Long id;
	private DeleteQuestionResult status;
	
	public DeleteQuestionDto() {
		// TODO Auto-generated constructor stub
	}
	
	public DeleteQuestionDto(Long id, DeleteQuestionResult status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DeleteQuestionResult getStatus() {
		return status;
	}

	public void setStatus(DeleteQuestionResult status) {
		this.status = status;
	}
}
