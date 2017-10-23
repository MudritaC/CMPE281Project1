package org.CMPE281.filemanager.constant;

public class FileManagerConstants {

    //File CRUD related constants
    public static final String INSERT_FILE_URL = "/insertFile";
    public static final String UPDATE_FILE_URL = "/updateFile";
    public static final String DELETE_FILE_URL = "/deleteFile";
    public static final String VIEW_USER_FILE_URL = "/viewFilesForUser";
    public static final String GET_ALL_FILES_URL = "/getAllFiles";

    //File AWS related constants
    public static final String S3_UPLOAD_FILE_URL = "/s3UploadFiles";
    public static final String DELETE_S3_FILE_URL = "/s3DeleteFile";
    public static final String S3_UPDATE_FILE_URL = "/s3UpdateFiles";


    public static final String GET_S3_FILE_URL = "/getS3FileURL";
    public static final String GET_CLOUDFRONT_FILE_URL = "/getCloudFrontFileURL";

    //User CRUD related constants
    public static final String USER_COUNT_URL ="/UserCount";
    public static final String INSERT_USER_URL = "/InsertUser";
    public static final String LOGIN_USER_URL = "/loginUser";
    public static final String UPDATE_USER_URL ="/UpdateUser";
    public static final String DELETE_USER_URL ="/DeleteUser";
    public static final String VIEW_USER_URL ="/ViewUser";
    public static final String VIEW_USER_BY_ID_URL ="/ViewUserById";
    public static final String VERIFY_USER_BY_ID ="/verifyUserId";




}
