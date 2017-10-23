package org.CMPE281.filemanager.dal;



import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.CMPE281.filemanager.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
/**
 * 
 * @author mudrita
 *
 */
public class FileDaoImpl {
	
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
	
	//method to insert file
	/**
	 * 
	 * @param file
	 * @return
	 */
	public String insertFile ( File file) {


		String sql = "INSERT INTO FILE_DETAILS("+
				//"FILE_ID,"+
				"USER_ID,"+
				"FILE_NAME,"+
				"FILE_DSC,"+
				"FILE_URL,"+
				"VERSION,"+
				"CREATED_DATE)"+
				"VALUES (?,?,?,?,?,?)";
		Object[] params = new Object[] {file.getUserId(),file.getFileName(),file.getFileDsc(),file.getFileUrl(),file.getVersion(), new Date()};
		try {
			jdbcTemplate.update(sql, params);
			return("File Uploaded Successfully");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return("FAILURE");


	}
	
	//method to update file
	public String updateFile ( File file) {

		String sql = "UPDATE FILE_DETAILS SET "+
				"USER_ID = ? ,"+
				//"FILE_NAME = ? ,"+
				"FILE_DSC = ? ,"+
				"FILE_URL = ? ,"+
				"VERSION = ? ,"+
				"MODIFIED_DATE = ? "+
				"WHERE FILE_NAME = ? ";
		Object[] params = new Object[] {file.getUserId(), file.getFileDsc(),file.getFileUrl(),file.getVersion(),new Date(),file.getFileName()};
		try {
			jdbcTemplate.update(sql, params);
			return("SUCCESS");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return("FAILURE");


	}
	
	//method to delete file
	public String deleteFile ( String name) {

		String sql = "DELETE FROM FILE_DETAILS "+
				"WHERE FILE_NAME = ? ";
		Object[] params = new Object[] {name};
		try {
			jdbcTemplate.update(sql, params);
			return("SUCCESS");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return("FAILURE");


	}
	
	//method to view all files
	public List<File> getFiles () {
		String sql = "SELECT * FROM FILE_DETAILS ";
		List<File> fileLst = jdbcTemplate.query(sql, new FileMapper());
		//System.out.println(ls);
		for (File file : fileLst){
			System.out.println(file.getUserId()+ " "+ file.getFileName()+ " " + file.getFileUrl()+ " "+ file.getCreatedDate() + " "+ file.getModifiedDate());
		}
		return fileLst;
	}
	
	
	//method to view all files
	//Another way of retrieving data
	
	
	/*public List<File> getFiles () {
		String sql = "SELECT * FROM FILE_DETAILS ";
		List<Map<String, Object>>  resultMapList = jdbcTemplate.queryForList(sql);

		List<File> fileList = new ArrayList<File>();
		for(Map<String, Object> resultMap : resultMapList ) {
			File file = new File();
			file.setFileId((String)resultMap.get("FILE_ID"));
			fileList.add(file);
		}
		return fileList;
	}*/
	
	
	//method to view all files for a particular user
	public List<File> getFilesByUserID(String userId){
		String sql = "SELECT * FROM FILE_DETAILS WHERE USER_ID = ? ";
		List<File> fileLst = jdbcTemplate.query(sql, new Object[] {userId},new FileMapper());
		return fileLst;
	}
	
	//method to view one file
	public File getFileById (String fileId) {

		String sql = "SELECT FILE_ID, USER_ID, FILE_NAME, FILE_DSC, FILE_URL, CREATED_DATE, MODIFIED_DATE "+
				"FROM FILE_DETAILS WHERE FILE_ID = ? ";
		System.out.println(jdbcTemplate.queryForObject(sql, new Object[] {fileId}, new FileMapper()));
		return jdbcTemplate.queryForObject(sql, new Object[] {fileId}, new FileMapper());


	}

}
