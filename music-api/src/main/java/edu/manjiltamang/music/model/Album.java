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
    private String id;
    private String artistId;
    private String artistName;
    private int totalSongs;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbSortKey
    public String getArtistId() {
        return artistId;
    }

    @Override
    public String toString() {
        return String.format("Album{%s::%s::%s}", id, artistId, super.getTitle());
    }
}