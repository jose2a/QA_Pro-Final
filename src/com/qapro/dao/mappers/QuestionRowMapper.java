package com.qapro.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.qapro.entity.Question;

public class QuestionRowMapper implements SqlRowMapper<Question> {

	public Question mapSqlToObject(ResultSet resultSet) throws SQLException {

		Question question = new Question();
		question.setId(resultSet.getLong(1));
		question.setText(resultSet.getString(2));
		question.setInsertDate(resultSet.getDate(3));
		question.setActive(resultSet.getBoolean(4));
		question.setAskerId(resultSet.getLong(5));
		question.setUpVote(resultSet.getLong(6));
		question.setDownVote(resultSet.getLong(7));

		return question;
	}

}