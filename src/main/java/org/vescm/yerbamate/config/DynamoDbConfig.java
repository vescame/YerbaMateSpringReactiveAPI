package org.vescm.yerbamate.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoDbConfig {
    private final String amazonDynamoDBEndpoint;
    private final String amazonAWSAccessKey;
    private final String amazonAWSSecretKey;

    public DynamoDbConfig(@Value("${amazon.dynamodb.endpoint}") final String amazonDynamoDBEndpoint,
                          @Value("${amazon.aws.accesskey}") final String amazonAWSAccessKey,
                          @Value("${amazon.aws.secretkey}") final String amazonAWSSecretKey) {
        this.amazonDynamoDBEndpoint = amazonDynamoDBEndpoint;
        this.amazonAWSAccessKey = amazonAWSAccessKey;
        this.amazonAWSSecretKey = amazonAWSSecretKey;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(final AWSCredentialsProvider awsCredentialsProvider) {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(amazonDynamoDBEndpoint, Regions.SA_EAST_1.getName()))
                .withCredentials(awsCredentialsProvider)
//                .withRegion(Regions.SA_EAST_1)
                .build();
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(awsCredentials());
    }

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
