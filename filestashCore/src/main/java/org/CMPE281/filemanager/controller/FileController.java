package org.CMPE281.filemanager.controller;

import java.util.Date;
import java.util.List;

import org.CMPE281.filemanager.dal.FileDaoImpl;
import org.CMPE281.filemanager.dal.s3uploadDaoImpl;
import org.CMPE281.filemanager.model.File;
import org.CMPE281.filemanager.model.UploadResponseVO;
import org.CMPE281.filemanager.model.User;
import org.CMPE281.filemanager.service.FileService;
import org.CMPE281.filemanager.service.S3UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.CMPE281.filemanager.constant.FileManagerConstants.*;

@Controller
@RequestMapping(value="/File")
public class FileController {
	private S3UploadService s3UploadService;
	private FileService fileService;

	public S3UploadService getS3UploadService() {
		return s3UploadService;
	}

	public void setS3UploadService(S3UploadService s3UploadService) {
		this.s3UploadService = s3UploadService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public FileService getFileService() {
		return fileService;
	}



	@RequestMapping(value=INSERT_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody UploadResponseVO insertFile (@RequestBody File file) {
		//getting file url from s3
		file.setFileUrl(s3UploadService.getcloudFrontFileURL(file.getFileName()));
		return fileService.insertFile(file);
	}

	@RequestMapping(value=UPDATE_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody UploadResponseVO updateFile (@RequestBody File file) {
		//getting file url from s3
		file.setFileUrl(s3UploadService.getFileURL(file.getFileName()));
		return fileService.updateFile(file);
	}
	
	
	@RequestMapping(value=DELETE_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody String deleteFile (@RequestBody File file) {
		fileService.deleteFile(file.getFileId());
		return "File Deleted";
	}

	@RequestMapping(value=VIEW_USER_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody List<File> getFiles (@RequestBody User user) {
		List<File> filesForUser = fileService.getFilesByUserID(user.getUserID());
		return filesForUser;
	}
	
	
	@RequestMapping(value=GET_ALL_FILES_URL, method=RequestMethod.GET)
	public @ResponseBody List<File> getAllFiles () {
		List<File> allFiles = fileService.getFiles();
		return allFiles;
	}


	@RequestMapping(value=S3_UPLOAD_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody
	UploadResponseVO s3UploadFiles (@RequestParam("file")MultipartFile file, @RequestParam("name")String name) {
		File newFile = new File();
		newFile.setFileName(name);
		newFile.setUplaodedFile(file);
		return s3UploadService.s3Fileupload(newFile);
	}

	@RequestMapping(value=GET_S3_FILE_URL, method=RequestMethod.GET)
	public @ResponseBody String getFileURL (@RequestBody String fileName) {
		return s3UploadService.getFileURL(fileName);
	}

	@RequestMapping(value=GET_CLOUDFRONT_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody String getcloudFrontFileURL (@RequestBody String fileName) {
		return s3UploadService.getcloudFrontFileURL(fileName);
	}

	@RequestMapping(value=DELETE_S3_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody UploadResponseVO deletFileFromS3 (@RequestBody File file) {
		if ("SUCCESS".equals(s3UploadService.deleteFile(file.getFileName()))){
			return fileService.deleteFile(file.getFileName());
		}
		else{
			UploadResponseVO errUploadRsp = new UploadResponseVO();
			errUploadRsp.setStatus("FAILED");
			errUploadRsp.setUploadEntity("S3 DELETION");
			return errUploadRsp;
		}
	}


	@RequestMapping(value=S3_UPDATE_FILE_URL, method=RequestMethod.POST)
	public @ResponseBody
	UploadResponseVO s3UpdateFiles (@RequestParam("file")MultipartFile file, @RequestParam("fileDesc")String fileDesc,
									@RequestParam("userId")String userId, @RequestParam("fileName")String fileName,
									@RequestParam("version")String version) {
		File newFile = new File();
		newFile.setFileName(fileName);
		newFile.setFileDsc(fileDesc);
		newFile.setVersion(Integer.parseInt(version));
		newFile.setUserId(userId);
		newFile.setModifiedDate(new Date().toString());
		newFile.setUplaodedFile(file);
		UploadResponseVO uploadResponseVO = s3UploadService.s3Fileupload(newFile);
		if(uploadResponseVO.getStatus().equals("SUCCESS")){
			//Update the DB.
			String url = s3UploadService.getcloudFrontFileURL(newFile.getFileName());
			newFile.setFileUrl(url);
			uploadResponseVO = fileService.updateFile(newFile);
			return uploadResponseVO;
		}
		else {
			uploadResponseVO.setUploadEntity("Update File");
			uploadResponseVO.setStatus(uploadResponseVO.getStatus());
			return uploadResponseVO;

		}
	}
}
