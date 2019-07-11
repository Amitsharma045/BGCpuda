package com.bitGallon.complaintMgmt.util;

import java.io.File;
import java.io.IOException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class AmazonS3FilesManager {
	
	public S3ObjectInputStream getUploadedFile(String fileName) throws AmazonServiceException, SdkClientException, IOException {
		ObjectListing bucket = s3Client.listObjects("elasticbeanstalk-ap-south-1-926354342457");
		if (!s3Client.doesObjectExist(bucket.getBucketName(), Constants.COMPLAINT_IMAGE_LOC + fileName)) return null;
		S3Object s3Object = s3Client.getObject(bucket.getBucketName(), Constants.COMPLAINT_IMAGE_LOC + fileName);
		return s3Object.getObjectContent();
	}
	
	public void uploadedFile(File file) throws AmazonServiceException, SdkClientException, IOException {
		ObjectListing bucket = s3Client.listObjects("elasticbeanstalk-ap-south-1-926354342457");
		s3Client.putObject(bucket.getBucketName(),Constants.COMPLAINT_IMAGE_LOC+file.getName() , file);
		
	}
	
	final static AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
	public static void main(String[] args) {
		System.out.println(s3Client.listBuckets());
	}
}
