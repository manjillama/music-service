package edu.manjiltamang.music.repository;

import edu.manjiltamang.music.config.DynamoUtils;
import edu.manjiltamang.music.model.Album;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@Repository
public class AlbumDAO {
    private final DynamoDbTable<Album> albumsTable;

    public AlbumDAO(DynamoDbEnhancedClient enhancedClient){
        this.albumsTable = DynamoUtils.table(enhancedClient, Album.class);
    }

    public Album write(@Nonnull Album album){
        albumsTable.putItem(album);
        // Log write
        return album;
    }
}