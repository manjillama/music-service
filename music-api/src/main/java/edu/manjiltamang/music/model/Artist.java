package edu.manjiltamang.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class Artist {
    private String artistId;
    private String name;
    private long totalStreams;
    private String genre;
    private String country;
    private int debutYear;

    // Primary partition key (artistId) for the main table
    @DynamoDbPartitionKey
    public String getArtistId() {
        return artistId;
    }

    // Use genre or a static partition key in the GSI for global queries
    @DynamoDbSecondaryPartitionKey(indexNames = "TotalStreamsIndex")
    public String getGenre() {
        return genre;
    }

    // Use totalStreams as the sort key in the GSI for ordering by streams
    @DynamoDbSecondarySortKey(indexNames = "TotalStreamsIndex")
    public long getTotalStreams() {
        return totalStreams;
    }
}
