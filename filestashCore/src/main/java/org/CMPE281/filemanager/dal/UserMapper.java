package org.CMPE281.filemanager.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.CMPE281.filemanager.model.User;
import org.springframework.jdbc.core.RowMapper;


public  class UserMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User usr = new User();
		try {
			usr.setUserID(rs.getString("USER_ID"));
			usr.setFirstname(rs.getString("FIRST_NAME"));
			usr.setLastname(rs.getString("LAST_NAME"));
			usr.setCreatedDate(rs.getString("CREATED_DATE"));
			usr.setModifiedDate(rs.getString("MODIFIED_DATE"));
			usr.setPassword(rs.getString("PASSWORD"));
		} catch (Exception ex) {
			System.out.println("Exception in mapper");
		}
		return usr;
	}
}
