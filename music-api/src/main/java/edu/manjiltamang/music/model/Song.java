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

    private String artistId;  // Partition Key
    private String songId;    // Sort Key
    private String albumId;
    private String lyrics;

    // Partition key for the main table (artistId)
    @DynamoDbPartitionKey
    public String getArtistId() {
        return artistId;
    }

    // Sort key for the main table (songId)
    @DynamoDbSortKey
    public String getSongId() {
        return songId;
    }

    // GSI for querying songs by genre
    @Override
    @DynamoDbSecondaryPartitionKey(indexNames = "GenreIndex")
    public String getGenre() {
        return super.getGenre();
    }

    // LSI for querying songs by albumId (within the same artist)
    @DynamoDbSecondarySortKey(indexNames = "AlbumIndex")
    public String getAlbumId() {
        return albumId;
    }
}