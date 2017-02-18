package com.qapro.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.qapro.entity.EmailValidation;

public class EmailValidationRowMapper implements SqlRowMapper<EmailValidation>{

	public EmailValidation mapSqlToObject(ResultSet resultSet)
			throws SQLException {
		
		EmailValidation emv = new EmailValidation();
		emv.setValidationId(resultSet.getLong(1));
		emv.setValidationCode(resultSet.getString(2));
		emv.setEmail(resultSet.getString(3));
		emv.setUserId(resultSet.getLong(4));
		emv.setValidated(resultSet.getBoolean(5));
		emv.setInsertDate(resultSet.getDate(6));
		emv.setValidationDate(resultSet.getDate(7));
		
		return emv;
	}
	
}