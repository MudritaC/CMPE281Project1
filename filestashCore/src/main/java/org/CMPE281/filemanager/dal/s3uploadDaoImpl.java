package org.CMPE281.filemanager.dal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import org.springframework.web.multipart.MultipartFile;


public class s3uploadDaoImpl {
    private static String bucketName     = "cmpe281mudrita";
    private static String cloudFront     = "dn7wl12fivi6c.cloudfront.net";

    public String s3Fileupload(org.CMPE281.filemanager.model.File file){
        AmazonS3 s3client=new AmazonS3Client(new ProfileCredentialsProvider());
        s3client.setRegion(Region.getRegion(Regions.US_WEST_2));
        s3client.setS3ClientOptions(S3ClientOptions.builder().setAccelerateModeEnabled(true).build());
        try{

            InputStream inputStream = file.getUplaodedFile().getInputStream();
            System.out.println("Uploading a new object to S3 from a file\n");
            s3client.putObject(new PutObjectRequest(bucketName,file.getFileName(), inputStream,new ObjectMetadata()));
            return "SUCCESS";

        }
        catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        catch (Exception ace) {
            System.out.println("Yayyy maza aa gaya");
        }
        return "FAILED";
    }



    public String getFileURL(String fileName){
        AmazonS3 s3client=new AmazonS3Client(new ProfileCredentialsProvider());
        S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName,fileName));
     return s3Object.getObjectContent().getHttpRequest().getURI().toString();
    }

    public String getcloudFrontFileURL(String fileName){
        System.out.println("http://"+cloudFront+"/"+fileName);
        return "http://"+cloudFront+"/"+fileName;
    }


    public String deleteFile(String fileName){
        AmazonS3 s3client=new AmazonS3Client(new ProfileCredentialsProvider());
        s3client.deleteObject(new DeleteObjectRequest(bucketName,fileName));
        return "SUCCESS";
    }


}

