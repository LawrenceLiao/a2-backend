package com.lawrence.assign2.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "subscription")
public class Subscription {

    @DynamoDBAttribute(attributeName = "subscriber_email")
    @DynamoDBHashKey
    private String email;

    @DynamoDBAttribute(attributeName = "music_title")
    @DynamoDBRangeKey
    private String title;

    @DynamoDBAttribute(attributeName = "artist")
    private String artist;

    @DynamoDBAttribute(attributeName = "year")
    private String year;

}
