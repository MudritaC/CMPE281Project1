package org.CMPE281.filemanager.service;

import org.CMPE281.filemanager.dal.s3uploadDaoImpl;
import org.CMPE281.filemanager.model.File;
import org.CMPE281.filemanager.model.UploadResponseVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * S3UploadService to get the data from AWS and manipulate before passing to UI
 */
public class S3UploadService {
    private s3uploadDaoImpl s3UploadDaoImpl;

    public UploadResponseVO getUploadResponseVO() {
        return uploadResponseVO;
    }

    public void setUploadResponseVO(UploadResponseVO uploadResponseVO) {
        this.uploadResponseVO = uploadResponseVO;
    }

    private UploadResponseVO uploadResponseVO;


    public void setS3UploadDaoImpl(s3uploadDaoImpl s3UploadDaoImpl) {
        this.s3UploadDaoImpl = s3UploadDaoImpl;
    }

    public s3uploadDaoImpl getS3UploadDaoImpl() {
        return s3UploadDaoImpl;
    }

    public UploadResponseVO s3Fileupload(File fileuploaded){
        String status = null;
        try{
            status = s3UploadDaoImpl.s3Fileupload(fileuploaded);
            uploadResponseVO.setStatus(status);
            uploadResponseVO.setUploadEntity("File");
        }
        catch(Exception e){

        }
        return uploadResponseVO;
    }

    public String getFileURL(String fileName){
        return s3UploadDaoImpl.getFileURL(fileName);
    }

    public String getcloudFrontFileURL(String fileName){
        return s3UploadDaoImpl.getcloudFrontFileURL(fileName);
    }

    public String deleteFile(String fileName){
        return s3UploadDaoImpl.deleteFile(fileName);
    }
}
