package com.lawrence.assign2.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.lawrence.assign2.entities.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArtistRepository {

    private final DynamoDBMapper mapper;

    public Artist getArtistByName(String name) {
        return mapper.load(Artist.class, name);
    }
}
