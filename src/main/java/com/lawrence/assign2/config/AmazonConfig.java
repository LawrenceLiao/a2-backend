package com.lawrence.assign2.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    private static final String DB_SERVICE_ENDPOINT = "dynamodb.us-east-1.amazonaws.com";
    private static final String S3_SERVICE_ENDPOINT = "s3.us-east-1.amazonaws.com";
    private static final String REGION = "us-east-1";
    private static final String ACCESS_KEY = "AKIAXYFUMLHTB5NKXOMK";
    private static final String SECRET_KEY = "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";


    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                ACCESS_KEY,
                SECRET_KEY
        );

        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(S3_SERVICE_ENDPOINT,REGION))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }

    private AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DB_SERVICE_ENDPOINT,REGION))
                .withCredentials(new AWSStaticCredentialsProvider( new BasicAWSCredentials(
                        ACCESS_KEY,
                        SECRET_KEY
                )))
                .build();


    }
}
