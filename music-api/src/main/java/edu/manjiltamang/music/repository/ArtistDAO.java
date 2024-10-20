package edu.manjiltamang.music.repository;

import edu.manjiltamang.music.config.DynamoUtils;
import edu.manjiltamang.music.model.Artist;
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
public class ArtistDAO {
    private static final Logger LOG = LoggerFactory.getLogger(ArtistDAO.class);
    private final DynamoDbTable<Artist> artistTable;

    public ArtistDAO(DynamoDbEnhancedClient enhancedClient) {
        this.artistTable = DynamoUtils.table(enhancedClient, Artist.class);
    }

    public Artist writeIfNotExists(@Nonnull Artist artist) {
        try {
            artistTable.putItem(PutItemEnhancedRequest.builder(Artist.class)
                    .item(artist)
                    .conditionExpression(Expression.builder()
                            .expression("attribute_not_exists(id)")
                            .build())
                    .build());
            LOG.debug("Created {}", artist);
        } catch (ConditionalCheckFailedException ex) {
            LOG.debug("Did not write {} because it already existed", artist);
        }
        return artist;
    }

    public Optional<Artist> getArtist(@Nonnull String artistId) {
        Artist artist = artistTable
                .getItem(Key.builder()
                        .partitionValue(artistId)
                        .build());

        return Optional.ofNullable(artist);
    }
}