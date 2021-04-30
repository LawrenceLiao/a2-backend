package com.lawrence.assign2.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.lawrence.assign2.entities.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository {
    private final DynamoDBMapper mapper;

    public Subscription add(Subscription subscription) {
        mapper.save(subscription);
        return subscription;
    }

    public int delete(Subscription subscription) {
        mapper.delete(subscription);
        return 1;
    }

    public List<Subscription> findListByEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue().withS(email));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("subscriber_email = :v1")
                .withExpressionAttributeValues(eav);


        List<Subscription> listOfSubscription = mapper.scan(Subscription.class, scanExpression);
        return listOfSubscription;
    }
}
