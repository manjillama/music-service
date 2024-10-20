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
    private String id;
    private String name;
    private long totalStreams;
    private String country;
    private int debutYear;

    // Primary partition key (artistId) for the main table
    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Artist{%s::%s}", id, name);
    }
}
