package com.qapro.dao;

import java.util.Date;
import java.util.List;

import com.qapro.dao.mappers.QuestionAskerRowMapper;
import com.qapro.dao.mappers.QuestionRowMapper;
import com.qapro.entity.Question;
import com.qapro.enums.VotingEnum;

public class QuestionDao extends JavathlonJdbcTemplate<Question> {

	private String insertQuestionSql = "INSERT INTO qapro.question (text, insert_date, is_active, asker_id, up_vote, down_vote)"
			+ " VALUES (:text, :insert_date, :is_active, :asker_id, :up_vote, :down_vote)";

	private String updteQuestionSql = "UPDATE qapro.question SET text = :text, is_active = :is_active"
			+ " WHERE asker_id = :asker_id";

	private String deleteQuestionSql = "DELETE FROM qapro.question WHERE id = :question_id";

	private String selectQuestionSql = "SELECT id, text, insert_date, is_active, asker_id, up_vote, down_vote FROM question";

	private String selectQuestionWithAskerSql = "SELECT id, text, q.insert_date, q.is_active, user_name, up_vote, down_vote"
			+ " FROM question q INNER JOIN user u ON q.asker_id = u.user_id WHERE q.is_active = true";

	private String selectQuestionsByUserIdSql = "SELECT id, text, insert_date, is_active, asker_id, up_vote, down_vote"
			+ " FROM question WHERE asker_id = :asker_id";

	public Long insertQuestion(Question question) throws Exception {

		SqlParameterValues values = new SqlParameterValues();
		values.addValue("text", question.getText());
		values.addValue("insert_date", new Date());
		values.addValue("is_active", question.getActive());
		values.addValue("asker_id", question.getAskerId());
		values.addValue("up_vote", 0L);
		values.addValue("down_vote", 0L);

		return this.insertItem(insertQuestionSql, values);
	}

	public void updateQuestion(Question question) throws Exception {

		SqlParameterValues values = new SqlParameterValues();
		values.addValue("text", question.getText());
		values.addValue("is_active", question.getActive());
		values.addValue("asker_id", question.getAskerId());

		this.update(updteQuestionSql, values);
	}

	public void deleteQuestion(long questionId) throws Exception {

		this.update(deleteQuestionSql, new SqlParameterValues().addValue("question_id", questionId));
	}

	public List<Question> getQuestionsWithAskerUsername() throws Exception {

		List<Question> questionList = this.getList(selectQuestionWithAskerSql, new SqlParameterValues(),
				new QuestionAskerRowMapper());

		if (questionList != null && questionList.size() > 0) {
			return questionList;
		}

		return null;
	}

	public List<Question> getQuestionsByUserId(Long userId) throws Exception {

		List<Question> questionList = this.getList(selectQuestionsByUserIdSql,
				new SqlParameterValues().addValue("asker_id", userId), new QuestionRowMapper());

		return questionList;
	}

	public Question getQuestionById(Long id) throws Exception {
		String sql = selectQuestionSql + " WHERE id = :question_id";

		List<Question> questionList = this.getList(sql, new SqlParameterValues().addValue("question_id", id),
				new QuestionRowMapper());

		if (questionList != null && questionList.size() > 0)
			return questionList.get(0);
		else
			return null;
	}

	public Question getQuestionByAskerId(Long id) throws Exception {
		String sql = selectQuestionSql + " WHERE asker_id = :asker_id";

		List<Question> questionList = this.getList(sql, new SqlParameterValues().addValue("asker_id", id),
				new QuestionRowMapper());

		if (questionList != null && questionList.size() > 0)
			return questionList.get(0);
		else
			return null;

	}

	public void voteQuestion(Long questionId, VotingEnum type) throws Exception {

		String upVoteSql = "UPDATE question SET up_vote = up_vote + 1 WHERE id = :question_id";
		String downVoteSql = "UPDATE question SET down_vote = down_vote + 1 WHERE id = :question_id";

		if (VotingEnum.UP == type) {
			this.update(upVoteSql, new SqlParameterValues().addValue("question_id", questionId));
		} else if (VotingEnum.DOWN == type) {
			this.update(downVoteSql, new SqlParameterValues().addValue("question_id", questionId));
		}
	}

}