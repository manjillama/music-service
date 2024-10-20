package edu.manjiltamang.music.repository;

import edu.manjiltamang.music.config.DynamoUtils;
import edu.manjiltamang.music.model.Song;
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

@Repository
public class SongDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SongDAO.class);
    private final DynamoDbTable<Song> songTable;

    public SongDAO(DynamoDbEnhancedClient enhancedClient) {
        this.songTable = DynamoUtils.table(enhancedClient, Song.class);
    }

    public Song writeIfNotExists(@Nonnull Song song) {
        try {
            songTable.putItem(PutItemEnhancedRequest.builder(Song.class)
                    .item(song)
                    .conditionExpression(Expression.builder()
                            .expression("attribute_not_exists(id)")
                            .build())
                    .build());
            LOG.debug("Created {}", song);
        } catch (ConditionalCheckFailedException ex) {
            LOG.debug("Did not write {} because it already existed", song);
        }
        return song;
    }

    public Song getSong(@Nonnull String songId) {
        return songTable
                .getItem(Key.builder()
                        .partitionValue(songId)
                        .build());
    }
}