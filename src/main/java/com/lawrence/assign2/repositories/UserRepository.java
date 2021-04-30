package com.lawrence.assign2.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.lawrence.assign2.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final DynamoDBMapper mapper;

    public UserEntity addUser(UserEntity user) {
        mapper.save(user);
        return user;
    }

    public UserEntity findByEmail(String email) {
        UserEntity result = mapper.load(UserEntity.class, email);
        return result;
    }
}
