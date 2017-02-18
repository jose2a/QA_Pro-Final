package com.qapro.service;

import com.qapro.dao.QuestionDao;
import com.qapro.dao.VoteDao;
import com.qapro.entity.Vote;
import com.qapro.enums.VotingEnum;
import com.qapro.enums.VotingResult;

public class VoteService {
	
	public VoteDao voteDao = new VoteDao();
	public QuestionDao questionDao =  new QuestionDao();
	
	public VotingResult saveVote(Vote vote) throws Exception {
		Vote checkVote = voteDao.getVoteByQuestionIdAndUserId(vote.getQuestionId(), vote.getUserId());
		
		if (checkVote != null) {
			if (checkVote.getType() == VotingEnum.UP) {
				return VotingResult.ALREADY_VOTE_UP;
			}
			else {
				return VotingResult.ALREADY_VOTE_DOWN;
			}
		}
		
		voteDao.insertVote(vote);
		questionDao.voteQuestion(vote.getQuestionId(), vote.getType());
		
		return VotingResult.VOTING_SUCCESFULL;
	}

}
