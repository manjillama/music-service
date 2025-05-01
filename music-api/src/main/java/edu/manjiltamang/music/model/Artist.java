package edu.manjiltamang.music.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Data
@DynamoDbBean
public class Artist {
    private String id;
    private String name;
    private long totalStreams;
    private int debutYear;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Artist{%s::%s}", id, name);
    }
}
