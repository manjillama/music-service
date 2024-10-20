package edu.manjiltamang.music.dto;

import edu.manjiltamang.music.model.AlbumInfo;
import edu.manjiltamang.music.model.Media;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Value
public class SongDto extends Media implements Serializable {
    String id;
    String artistId;
    String artistName;
    String albumId;
    String albumTitle;
    String lyrics;
    long totalStreams;
}