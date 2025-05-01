package edu.manjiltamang.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDbBean
public class Song extends Media {
    private String id;
    private String artistId;
    private String artistName;
    private Album album;
    private String lyrics;
    private long totalStreams;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    // GSI for querying songs by genre
    @Override
    @DynamoDbSecondaryPartitionKey(indexNames = "GenreIndex")
    public String getGenre() {
        return super.getGenre();
    }

    @Override
    public String toString() {
        return String.format("Song{%s::%s::%s::%s}", id, artistId, super.getTitle(), album.getTitle());
    }
}