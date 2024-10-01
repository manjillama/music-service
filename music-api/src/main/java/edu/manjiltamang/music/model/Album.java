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
public class Album extends Media implements Serializable {
    private String albumId;
    private String artistId;

    @DynamoDbPartitionKey
    public String getAlbumId() {
        return albumId;
    }

    @DynamoDbSortKey
    public String getArtistId() {
        return artistId;
    }

    @Override
    @DynamoDbSecondaryPartitionKey(indexNames = "GenreIndex")
    public String getGenre() {
        return super.getGenre();
    }
}