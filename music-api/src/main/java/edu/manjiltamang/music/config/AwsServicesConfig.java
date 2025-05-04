package edu.manjiltamang.music.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class AwsServicesConfig {
    @Value("${aws.region}")
    Region region;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(awsCredentialsProvider())
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    public AwsCredentialsProvider awsCredentialsProvider() {
        String env = System.getenv("ENV");
        if ("dev".equalsIgnoreCase(env)) {
            // Reads from your local ~/.aws/credentials file:
            return ProfileCredentialsProvider.create("default");
        } else {
            /**
             * Looks for credentials in this order:
             * 	1. Environment variables:
             *     AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY
             * 	2. Java system properties:
             *     aws.accessKeyId, aws.secretAccessKey
             * 	3. Profile credentials file:
             *     ~/.aws/credentials
             * 	4. EC2/Lambda IAM role:
             *     Automatically retrieved from instance metadata
             */
            return DefaultCredentialsProvider.create();
        }
    }

}