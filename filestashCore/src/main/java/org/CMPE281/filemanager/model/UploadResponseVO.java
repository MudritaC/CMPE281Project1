package org.CMPE281.filemanager.model;

public class UploadResponseVO {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getUploadEntity() {
        return uploadEntity;
    }

    public void setUploadEntity(String uploadEntity) {
        this.uploadEntity = uploadEntity;
    }

    String uploadEntity;

}
