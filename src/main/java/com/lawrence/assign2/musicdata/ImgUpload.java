package com.lawrence.assign2.musicdata;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImgUpload {
    public static void main(String[] args) {
        Regions clientRegion = Regions.US_EAST_1;
        String bucketName = "cc-s3764177-a2";

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new ProfileCredentialsProvider("default"))
                .build();

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new ProfileCredentialsProvider("default"))
                .build();

        List<String> listOfImg = getListOfImg(client);

        for(String str : listOfImg) {
            if(downloadImg(str) != null) {

                String fileObjKeyName = str.substring(39);
                String fileName = downloadImg(str);

                try {
                    // Upload a file as a new object with ContentType and title specified.
                    PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentType("image/jpeg");
                    metadata.addUserMetadata("file", fileObjKeyName);
                    request.setMetadata(metadata);
                    s3Client.putObject(request);
                } catch (AmazonServiceException e) {
                    e.printStackTrace();
                } catch (SdkClientException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    private static List<String> getListOfImg(AmazonDynamoDB client) {
        List<String> listOfImg = new ArrayList<>();

        ScanRequest req = new ScanRequest()
                .withTableName("music");
        ScanResult result = client.scan(req);
        for (Map<String, AttributeValue> item : result.getItems()){
            listOfImg.add(item.get("img_url").getS());
        }
        return listOfImg;
    }

    private static String downloadImg(String imageUrl) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        boolean isDownloaded = false;
        String fileName = null;

        try {
            URL url = new URL(imageUrl);
            inputStream = url.openStream();
            fileName ="/Users/lawrenceliao/Desktop/cc_a2/" + imageUrl.substring(39);
            outputStream = new FileOutputStream(new File (fileName));

            byte[] buffer = new byte[2048];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            isDownloaded = true;

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException :- " + e.getMessage());

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException :- " + e.getMessage());

        } catch (IOException e) {
            System.out.println("IOException :- " + e.getMessage());

        } finally {
            try {
                inputStream.close();
                outputStream.close();


            } catch (IOException e) {
                System.out.println("Finally IOException :- " + e.getMessage());
            }
        }
        if(isDownloaded) {
            return fileName;
        }
        return null;
    }

}
