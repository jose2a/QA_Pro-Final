package com.qapro.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.qapro.entity.Vote;
import com.qapro.enums.VotingEnum;

public class VoteRowMapper implements SqlRowMapper<Vote> {

	public Vote mapSqlToObject(ResultSet resultSet) throws SQLException {
		Vote vote =  new Vote();
		vote.setId(resultSet.getLong(1));
		vote.setQuestionId(resultSet.getLong(2));
		vote.setUserId(resultSet.getLong(3));
		vote.setType(VotingEnum.valueOf(resultSet.getString(4)));
		vote.setDate(resultSet.getDate(5));
		vote.setIsCancelled(resultSet.getBoolean(6));
		
		return vote;
	}

}