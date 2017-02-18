package com.qapro.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.qapro.entity.Answer;

public class AnswerRowMapper implements SqlRowMapper<Answer> {

	public Answer mapSqlToObject(ResultSet resultSet) throws SQLException {
		Answer answer = new Answer();
		answer.setId(resultSet.getLong("id"));
		answer.setText(resultSet.getString("text"));
		answer.setInsertDate(resultSet.getDate("insert_date"));
		answer.setActive(resultSet.getBoolean("is_active"));
		answer.setResponderId(resultSet.getLong("responder_id"));
		answer.setQuestionId(resultSet.getLong("question_id"));
		
		return answer;
	}

}