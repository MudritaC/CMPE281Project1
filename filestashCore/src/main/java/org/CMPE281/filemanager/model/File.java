package org.CMPE281.filemanager.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class File implements Serializable{
	@JsonIgnore
	private static final long serialVersionUID = -7788619177798333712L;

	private String fileId;
	private String userId;
	private String fileName;
	private String fileDsc;
	private int version;

	private String fileUrl;
	private String createdDate;

	private String modifiedDate;
	private MultipartFile uplaodedFile;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}



	public MultipartFile getUplaodedFile() {
		return uplaodedFile;
	}

	public void setUplaodedFile(MultipartFile uplaodedFile) {
		this.uplaodedFile = uplaodedFile;
	}

	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDsc() {
		return fileDsc;
	}
	public void setFileDsc(String fileDsc) {
		this.fileDsc = fileDsc;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


}
