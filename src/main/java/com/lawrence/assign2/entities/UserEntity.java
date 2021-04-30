package com.lawrence.assign2.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "login")
public class UserEntity {

    @DynamoDBHashKey(attributeName = "email")
    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute(attributeName = "user_name")
    private String username;

    @DynamoDBAttribute(attributeName = "password")
    private String password;
}
