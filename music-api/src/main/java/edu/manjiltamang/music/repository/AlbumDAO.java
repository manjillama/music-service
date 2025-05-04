package edu.manjiltamang.music.repository;

import edu.manjiltamang.music.config.DynamoUtils;
import edu.manjiltamang.music.model.Album;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

import java.util.Optional;

@Repository
public class AlbumDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AlbumDAO.class);
    private final DynamoDbTable<Album> albumsTable;

    public AlbumDAO(DynamoDbEnhancedClient enhancedClient) {
        this.albumsTable = DynamoUtils.table(enhancedClient, Album.class);
    }

    public Album writeIfNotExists(@Nonnull Album album) {
        try {
            albumsTable.putItem(PutItemEnhancedRequest.builder(Album.class)
                    .item(album)
                    .conditionExpression(Expression.builder()
                            .expression("attribute_not_exists(id)")
                            .build())
                    .build());
            LOG.info("Created {}", album);
        } catch (ConditionalCheckFailedException ex) {
            LOG.info("Did not write {} because it already existed", album);
        }
        return album;
    }

    public Optional<Album> getAlbum(@Nonnull String albumId) {
        LOG.debug("Fetching album with ID {}", albumId);
        Album album = albumsTable
                .getItem(Key.builder()
                        .partitionValue(albumId)
                        .build());

        return Optional.ofNullable(album);
    }
}