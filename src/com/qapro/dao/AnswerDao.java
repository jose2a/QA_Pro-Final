package com.qapro.dao;

import java.util.Date;
import java.util.List;

import com.qapro.dao.mappers.AnswerRowMapper;
import com.qapro.entity.Answer;

public class AnswerDao extends JavathlonJdbcTemplate<Answer> {

	private String insertAnswerSql = "INSERT INTO answer (text, insert_date, is_active, responder_id, question_id)"
			+ " VALUES (:text ,	:insert_date , :is_active ,	:responder_id ,	:question_id )";
	
	private String selectAnswerSql = "select id,text,insert_date,is_active,responder_id,question_id from answer";
	
	public void insertAnswer(Answer answer) throws Exception {
		
		SqlParameterValues values = new SqlParameterValues();
		values.addValue("text", answer.getText());
		values.addValue("insert_date", new Date());
		values.addValue("is_active", true);
		values.addValue("responder_id", answer.getResponderId());
		values.addValue("question_id", answer.getQuestionId());

		this.insertItem(insertAnswerSql, values);
	}

	public List<Answer> getAnswersToQuestion(Long questionId) throws Exception {
		String sql = selectAnswerSql + " WHERE question_id = :question_id";

		return this.getList(sql, new SqlParameterValues().addValue("question_id", questionId), new AnswerRowMapper());
	}

	public Answer getAnswerById(Long id) throws Exception {
		String sql = selectAnswerSql + " WHERE id = :id";

		List<Answer> answerList = this.getList(sql, new SqlParameterValues().addValue("id", id), new AnswerRowMapper());
		
		if (answerList != null && answerList.size() > 0) {
			return answerList.get(0);
		} else {
			return null;
		}
	}
	
}