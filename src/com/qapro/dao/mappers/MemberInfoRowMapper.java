package com.qapro.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.qapro.entity.MemberInfo;
import com.qapro.dao.mappers.SqlRowMapper;

public class MemberInfoRowMapper implements SqlRowMapper<MemberInfo> {

	public MemberInfo mapSqlToObject(ResultSet resultSet) throws SQLException {
		MemberInfo member = new MemberInfo();
		member.setId(resultSet.getLong("user_id"));
		member.setUserName(resultSet.getString("user_name"));
		member.setName(resultSet.getString("name"));
		member.setSurname(resultSet.getString("surname"));
		member.setEmail(resultSet.getString("email"));
		member.setRegistrationTime(resultSet.getDate("insert_date"));
		member.setIsActive(resultSet.getBoolean("is_active"));
		
		return member;
	}

}