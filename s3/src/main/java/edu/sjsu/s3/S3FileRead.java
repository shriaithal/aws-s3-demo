package edu.sjsu.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@SuppressWarnings("deprecation")
public class S3FileRead {

	public void readS3File() {
		String bucketName = "cmpe282-bucket";
		String key = "hello.txt";

		String accessId = "";
		String secretKey = "";
		AWSCredentials s3Credentials = new BasicAWSCredentials(accessId, secretKey);
		AmazonS3 s3Client = new AmazonS3Client(s3Credentials);
		S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
		InputStream objectData = object.getObjectContent();
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(objectData));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					objectData.close();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
