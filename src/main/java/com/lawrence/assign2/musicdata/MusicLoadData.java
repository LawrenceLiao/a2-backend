package com.lawrence.assign2.musicdata;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.Iterator;

public class MusicLoadData {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new ProfileCredentialsProvider("default"))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("music");

        JsonParser parser = new JsonFactory().createParser(new File("a2.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ArrayNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ArrayNode) iter.next();

            Iterator<JsonNode> iterator = currentNode.iterator();
            ObjectNode objNode;
            while(iterator.hasNext()) {
                objNode = (ObjectNode) iterator.next();
                String artist = objNode.path("artist").asText();
                String title = objNode.path("title").asText();
                String year =  objNode.path("year").asText();
                String webUrl = objNode.path("web_url").asText();
                String imgUrl = objNode.path("img_url").asText();


                try {
                    table.putItem(new Item().withPrimaryKey("title", title, "artist", artist)
                            .with("year", year)
                            .with("web_url", webUrl)
                            .with("img_url", imgUrl));
                    System.out.println("PutItem succeeded: " + title + " By " + artist);

                } catch (Exception e) {
                    System.err.println("Unable to add movie: " + title + " By " + artist);
                    System.err.println(e.getMessage());
                    break;
                }
            }
        }
        parser.close();
    }
}
