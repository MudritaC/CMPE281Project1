package org.CMPE281.filemanager.dal;



import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.CMPE281.filemanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//function to insert user in db

	public String insertUser ( User usr) {

		String sql = "INSERT INTO USER("+
				"USER_ID,"+
				"FIRST_NAME,"+
				"LAST_NAME,"+
				"CREATED_DATE,"+
				"MODIFIED_DATE,"+
				"PASSWORD)"+
				"VALUES (?,?,?,?,?,?)";
		Object[] params = new Object[] {usr.getUserID(), usr.getFirstname(), usr.getLastname(), new Date(),new Date(),usr.getPassword()};
		try {
			jdbcTemplate.update(sql, params);
			return("SUCCESS");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return("FAILURE");


	}

	//function to update user in db

	public String updateUser ( User usr) {

		String sql = "UPDATE USER SET "+
				"FIRST_NAME = ? ,"+
				"LAST_NAME = ? ,"+
				"MODIFIED_DATE = ? "+
				"WHERE USER_ID = ? ";
		Object[] params = new Object[] {usr.getFirstname(), usr.getLastname(), new Date(),usr.getUserID()};
		try {
			jdbcTemplate.update(sql, params);
			return("SUCCESS");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return("FAILURE");


	}

	//function to delete user from db

	public String deleteUser ( String usrID) {

		String sql = "DELETE FROM USER "+
				"WHERE USER_ID = ? ";
		Object[] params = new Object[] {usrID};
		try {
			jdbcTemplate.update(sql, params);
			return("SUCCESS");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return("FAILURE");


	}

	//function to get user by ID from db

	public User getUsersById (String userId) {

		String sql = "SELECT USER_ID, FIRST_NAME, LAST_NAME, CREATED_DATE, MODIFIED_DATE,PASSWORD "+
				"FROM USER WHERE USER_ID = ? ";
		//System.out.println(jdbcTemplate.queryForObject(sql, new Object[] {userId}, new UserMapper()));
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {userId}, new UserMapper());
		}
		catch (Exception ex){
			return  null;
		}



	}

	//function to get all users from db

	public List<User> getUsers () {
		String sql = "SELECT * FROM USER ";
		List<User> ls = jdbcTemplate.query(sql, new UserMapper());
		//System.out.println(ls);
		for (User usr : ls){
			System.out.println(usr.getUserID()+ " "+ usr.getFirstname()+ " " + usr.getLastname()+ " "+ usr.getCreatedDate() + " "+ usr.getModifiedDate());
		}
		return ls;
	}



	public int getUserCount () {
		String sql = "SELECT COUNT(*) FROM USER";

		System.out.println("data returned" + jdbcTemplate.queryForInt(sql));
		return jdbcTemplate.queryForInt(sql);
	}

	//loging in user

	public User loginUser (User user) {

		String sql = "SELECT USER_ID, FIRST_NAME, LAST_NAME, CREATED_DATE, MODIFIED_DATE, PASSWORD "+
				"FROM USER WHERE USER_ID = ? AND PASSWORD = ? ";
		try {
			User usr = jdbcTemplate.queryForObject(sql, new Object[]{user.getUserID(), user.getPassword()}, new UserMapper());
			return usr;
		}
		catch (Exception ex){
			return null;
		}


	}




}


