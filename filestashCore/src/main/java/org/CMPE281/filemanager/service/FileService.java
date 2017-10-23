package org.CMPE281.filemanager.service;

import org.CMPE281.filemanager.dal.FileDaoImpl;
import org.CMPE281.filemanager.model.File;
import org.CMPE281.filemanager.model.UploadResponseVO;

import java.util.List;

/**
 * Service class to manipulate data from DB to pass to UI
 */
public class FileService {
    private FileDaoImpl fileDao;
    private UploadResponseVO uploadResponseVO;

    public UploadResponseVO getUploadResponseVO() {
        return uploadResponseVO;
    }

    public void setUploadResponseVO(UploadResponseVO uploadResponseVO) {
        this.uploadResponseVO = uploadResponseVO;
    }



    public void setFileDao(FileDaoImpl fileDao) {
        this.fileDao = fileDao;
    }

    public FileDaoImpl getFileDao() {
        return fileDao;
    }

    public UploadResponseVO insertFile(File file) {
       String status = fileDao.insertFile(file);
        uploadResponseVO.setStatus(status);
        uploadResponseVO.setUploadEntity("database insert");
        return uploadResponseVO;
    }

    public UploadResponseVO updateFile(File file) {
        int newVersion = file.getVersion();
        file.setVersion(newVersion);
        String status = fileDao.updateFile(file);
        uploadResponseVO.setStatus(status);
        uploadResponseVO.setUploadEntity("database update");
        return uploadResponseVO;
    }

    public UploadResponseVO deleteFile(String name) {
        uploadResponseVO.setStatus(fileDao.deleteFile(name));
        uploadResponseVO.setUploadEntity("database delete");
        return uploadResponseVO;
    }

    public List<File> getFilesByUserID(String userID) {
        List<File> fileList =fileDao.getFilesByUserID(userID);
        return fileList;
    }

    public List<File> getFiles() {
        List<File> fileList = fileDao.getFiles();
        return fileList;
    }
}
