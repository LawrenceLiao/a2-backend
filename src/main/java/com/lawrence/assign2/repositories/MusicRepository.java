package com.lawrence.assign2.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.lawrence.assign2.dtos.MusicGetDto;
import com.lawrence.assign2.entities.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MusicRepository {

    private final DynamoDBMapper mapper;

    public List<Music> getListByInfo(MusicGetDto musicGetDto) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue().withS(musicGetDto.getTitle()));
        eav.put(":v2",new AttributeValue().withS(musicGetDto.getArtist()));
        eav.put(":v3",new AttributeValue().withS(musicGetDto.getYear()));

        Map<String, String> expression = new HashMap<>();

        expression.put("#year", "year");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("title = :v1 and artist = :v2 and #year = :v3")
                .withExpressionAttributeNames(expression)
                .withExpressionAttributeValues(eav);


        List<Music> listOfMusic = mapper.scan(Music.class, scanExpression);
        return listOfMusic;
    }

    public List<Music> getListByTitle(MusicGetDto musicGetDto) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue().withS(musicGetDto.getTitle()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("title = :v1")
                .withExpressionAttributeValues(eav);

        List<Music> listOfMusic = mapper.scan(Music.class, scanExpression);
        return listOfMusic;
    }

    public List<Music> getListByArtist(MusicGetDto musicGetDto) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue().withS(musicGetDto.getArtist()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("artist = :v1")
                .withExpressionAttributeValues(eav);


        List<Music> listOfMusic = mapper.scan(Music.class, scanExpression);
        return listOfMusic;
    }

    public List<Music> getListByIYear(MusicGetDto musicGetDto) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue().withS(musicGetDto.getYear()));

        Map<String, String> expression = new HashMap<>();

        expression.put("#year", "year");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#year = :v1")
                .withExpressionAttributeNames(expression)
                .withExpressionAttributeValues(eav);

        List<Music> listOfMusic = mapper.scan(Music.class, scanExpression);
        return listOfMusic;
    }


}
