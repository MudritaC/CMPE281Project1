package org.CMPE281.filemanager.service;

import org.CMPE281.filemanager.dal.FileDaoImpl;
import org.CMPE281.filemanager.dal.UserDaoImpl;
import org.CMPE281.filemanager.model.UploadResponseVO;
import org.CMPE281.filemanager.model.User;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class UserService {

    private UserDaoImpl userDao;
    private UploadResponseVO uploadResponseVO;

    public UploadResponseVO getUploadResponseVO() {
        return uploadResponseVO;
    }

    public void setUploadResponseVO(UploadResponseVO uploadResponseVO) {
        this.uploadResponseVO = uploadResponseVO;
    }
    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UploadResponseVO insertUser (User usr) {
        String password = usr.getPassword();
        byte[] encodedBytes = Base64.encodeBase64(password.getBytes());
        String encryptedPwd = encryptPassword(new String(encodedBytes));

        usr.setPassword(encryptedPwd);

        String status = userDao.insertUser(usr);
        uploadResponseVO.setStatus(status);
        uploadResponseVO.setUploadEntity("database inserted ");
        return uploadResponseVO;
    }

    public User loginUser (User usr) {
        String password = usr.getPassword();
        byte[] encodedBytes = Base64.encodeBase64(password.getBytes());
        String encryptedPwd = encryptPassword(new String(encodedBytes));

        usr.setPassword(encryptedPwd);

        return userDao.loginUser(usr);
    }


    private String encryptPassword(String encodedPwd) {
        String algo = "SHA";
        StringBuilder sb = new StringBuilder();

        byte[] plainText = encodedPwd.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance(algo);

            md.reset();
            md.update(plainText);
            byte[] encodedPassword = md.digest();

            for (int i = 0; i < encodedPassword.length; i++) {
                if ((encodedPassword[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }
            System.out.println("Encrypted: " + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public boolean isUserPresent(String userId){
        User user = userDao.getUsersById(userId);
        if(user !=null){
            return true;
        }
        else{
            return false;
        }
    }

}
