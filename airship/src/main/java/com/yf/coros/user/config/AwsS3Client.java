package com.yf.coros.user.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Client {

    @Bean
    public static AmazonS3 amazonS3() {
        final BasicAWSCredentials awsCreds = new BasicAWSCredentials(Constants.AWS_ACCESS_KEY,
            Constants.AWS_SECRET_KEY);
        return AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .withRegion(Regions.US_WEST_1)
            .build();
    }


}
