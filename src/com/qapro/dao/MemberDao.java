package com.qapro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.qapro.dao.mappers.MemberInfoRowMapper;
import com.qapro.entity.MemberInfo;
import com.qapro.util.DBConnectionUtil;

public class MemberDao extends JavathlonJdbcTemplate<MemberInfo> {

	private String getAllMemberSql = "SELECT user_id, user_name, name, surname, email, insert_date, is_active FROM user";

	private String insertMemberSql = "INSERT INTO user(name, surname, email, user_name, is_active)"
			+ " VALUES ( :name , :surname , :email , :username , :is_active )";

	public List<MemberInfo> getAllMembers() throws Exception {

		return this.getList(getAllMemberSql, new SqlParameterValues(), new MemberInfoRowMapper());
	}

	public Long insertMember(MemberInfo m) throws Exception {

		SqlParameterValues values = new SqlParameterValues();
		values.addValue("name", m.getName());
		values.addValue("surname", m.getSurname());
		values.addValue("email", m.getEmail());
		values.addValue("username", m.getUserName());
		values.addValue("insert_date", new Date());
		values.addValue("is_active", true);

		return this.insertItem(insertMemberSql, values);
	}

	public MemberInfo getMemberByUserName(String name) throws Exception {
		String sql = getAllMemberSql + " WHERE user_name = :username";

		MemberInfo m = this.queryForObject(sql, new SqlParameterValues().addValue("username", name),
				new MemberInfoRowMapper());

		return m;
	}

	public MemberInfo getMemberById(Long id) throws Exception {
		String sql = getAllMemberSql + " WHERE user_id = :id";
		
		MemberInfo m = this.queryForObject(sql, new SqlParameterValues().addValue("id", id), new MemberInfoRowMapper());
		
		return m;
	}

	public MemberInfo verifyUserCredentials(String email, String password) throws Exception {

		String sql = getAllMemberSql + " WHERE email = :email AND password = :password";

		MemberInfo m = this.queryForObject(sql,
				new SqlParameterValues().addValue("email", email).addValue("password", password),
				new MemberInfoRowMapper());

		return m;
	}
	
	public ArrayList<String> getRolePermissions(String roleName) throws Exception {
		String rolePermissionSql = "SELECT permission_name, is_active FROM role_permission WHERE role_name = ?";
		
		ArrayList<String> permissionList = new ArrayList<String>();
		
		Connection connection = DBConnectionUtil.getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(rolePermissionSql);
		preparedStatement.setString(1, roleName);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			String priviligeName = resultSet.getString(1);
			boolean isActivePermission = resultSet.getBoolean(2);
			
			if (isActivePermission)
				permissionList.add(priviligeName);
		}
		
		return permissionList;
	}
	
}