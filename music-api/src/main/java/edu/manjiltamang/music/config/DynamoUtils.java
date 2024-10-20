package edu.manjiltamang.music.config;

import edu.manjiltamang.music.model.Album;
import edu.manjiltamang.music.model.Artist;
import edu.manjiltamang.music.model.Song;
import edu.manjiltamang.music.utils.Constants;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Map;

public class DynamoUtils {
    //    private static final Logger LOG = LoggerFactory.getLogger(DynamoUtils.class);
    private static final Map<Class<?>, String> DYNAMO_TABLES = Map.of(
        Album.class, Constants.ALBUMS_TABLE,
        Artist.class, Constants.ARTISTS_TABLE,
        Song.class, Constants.SONGS_TABLE);

    public static String tableName(Class<?> cls) {
        var table = DYNAMO_TABLES.get(cls);
        if (table == null) {
            throw new IllegalArgumentException("Unknown table: " + cls);
        }
        return table;
    }

    public static <T> DynamoDbTable<T> table(DynamoDbEnhancedClient enhancedClient, Class<T> cls) {
        return enhancedClient.table(DynamoUtils.tableName(cls), TableSchema.fromBean(cls));
    }
}
