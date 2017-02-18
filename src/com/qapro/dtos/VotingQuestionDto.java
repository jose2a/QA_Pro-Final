package com.qapro.dtos;

public class VotingQuestionDto {

	private String status;
	private Long voteUp;
	private Long voteDown;

	public VotingQuestionDto() {
		// TODO Auto-generated constructor stub
	}

	public VotingQuestionDto(String status, Long voteUp, Long voteDown) {
		super();
		this.status = status;
		this.voteUp = voteUp;
		this.voteDown = voteDown;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getVoteUp() {
		return voteUp;
	}

	public void setVoteUp(Long voteUp) {
		this.voteUp = voteUp;
	}

	public Long getVoteDown() {
		return voteDown;
	}

	public void setVoteDown(Long voteDown) {
		this.voteDown = voteDown;
	}
}
