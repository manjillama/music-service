package edu.manjiltamang.music.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
public class AlbumInfo {
    private String id;
    private String title;
}