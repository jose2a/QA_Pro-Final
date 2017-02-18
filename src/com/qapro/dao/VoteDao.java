package com.qapro.dao;

import java.util.List;

import com.qapro.dao.mappers.VoteRowMapper;
import com.qapro.entity.Vote;

public class VoteDao extends JavathlonJdbcTemplate<Vote> {

	private String getAllVotes = "SELECT id, question_id, user_id, type, date, is_cancelled FROM vote";
	private String insertVote = "INSERT INTO vote(question_id, user_id, type, date, is_cancelled)"
			+ " VALUES (:question_id , :user_id , :type , :date , :is_cancelled )";

	public Vote getVoteByQuestionIdAndUserId(Long questionId, Long userId) throws Exception {
		String sql = getAllVotes + " WHERE question_id = :question_id and user_id = :user_id";

		List<Vote> voteList = this.getList(sql,
				new SqlParameterValues().addValue("question_id", questionId).addValue("user_id", userId),
				new VoteRowMapper());

		if (voteList != null && voteList.size() > 0) {
			return voteList.get(0);
		}
		return null;
	}

	public void insertVote(Vote vote) throws Exception {
		SqlParameterValues values = new SqlParameterValues();
		values.addValue("question_id", vote.getQuestionId());
		values.addValue("user_id", vote.getUserId());
		values.addValue("type", vote.getType().name());
		values.addValue("date", vote.getDate());
		values.addValue("is_cancelled", vote.getIsCancelled());

		this.insertItem(insertVote, values);
	}

}
