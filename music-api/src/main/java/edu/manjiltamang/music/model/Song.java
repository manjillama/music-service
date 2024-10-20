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
public class Song extends Media implements Serializable {
    private String id;
    private String artistId;
    private String artistName;
    private AlbumInfo album;
    private String lyrics;
    private long totalStreams;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbSortKey
    public String getArtistId() {
        return artistId;
    }

    // GSI for querying songs by genre
    @Override
    @DynamoDbSecondaryPartitionKey(indexNames = "GenreIndex")
    public String getGenre() {
        return super.getGenre();
    }

    @DynamoDbSecondarySortKey(indexNames = "TotalStreamsIndex")
    public long getTotalStreams() {
        return totalStreams;
    }

    @DynamoDbAttribute("album")
    public AlbumInfo getAlbum() {
        return album;
    }

    @Override
    public String toString() {
        return String.format("Song{%s::%s::%s::%s}", id, artistId, super.getTitle(), album.getTitle());
    }
}