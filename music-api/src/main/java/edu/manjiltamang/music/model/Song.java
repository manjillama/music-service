package edu.manjiltamang.music.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Data
@DynamoDbBean
public class Song {
    private String id;
    private String artistId;
    private String albumId;
    private Album album;
    private String lyrics;
    private long totalStreams;
    private String title;
    private int releaseYear;
    private String genre;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    // GSI for querying songs by genre
    @DynamoDbSecondaryPartitionKey(indexNames = "GenreIndex")
    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return String.format("Song{%s::%s::%s::%s}", id, artistId, title, albumId);
    }
}