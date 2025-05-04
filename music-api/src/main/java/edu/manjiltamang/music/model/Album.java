package edu.manjiltamang.music.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@Data
@DynamoDbBean
public class Album {
    private String id;
    private String artistId;
    private int totalSongs;
    private String title;
    private int releaseYear;
    private String genre;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "ArtistIndex")
    public String getArtistId() {
        return artistId;
    }

    @Override
    public String toString() {
        return String.format("Album{%s::%s::%s}", id, artistId, title);
    }
}