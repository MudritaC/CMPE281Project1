package org.CMPE281.filemanager.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.CMPE281.filemanager.model.File;
import org.springframework.jdbc.core.RowMapper;

public class FileMapper implements RowMapper<File>{

	@Override
	public File mapRow(ResultSet rs, int rowNum) throws SQLException {
		File file = new File();
		file.setFileId(rs.getString("FILE_ID"));
		file.setUserId(rs.getString("USER_ID"));
		file.setFileName(rs.getString("FILE_NAME"));
		file.setFileDsc(rs.getString("FILE_DSC"));
		file.setFileUrl(rs.getString("FILE_URL"));
		file.setCreatedDate(rs.getString("CREATED_DATE"));
		file.setModifiedDate(rs.getString("MODIFIED_DATE"));
		file.setVersion(Integer.parseInt(rs.getString("VERSION")));

		return file;
	}

}
