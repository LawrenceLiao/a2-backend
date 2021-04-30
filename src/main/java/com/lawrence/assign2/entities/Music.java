package com.lawrence.assign2.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "music")
public class Music {

    @DynamoDBAttribute
    @DynamoDBHashKey
    private String title;

    @DynamoDBAttribute
    @DynamoDBRangeKey
    private String artist;

    @DynamoDBAttribute
    private String webUrl;

    @DynamoDBAttribute
    private String imgUrl;

    @DynamoDBAttribute
    private String year;
}
