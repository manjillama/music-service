package edu.manjiltamang.music.dto;

import edu.manjiltamang.music.model.Media;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Value
public class AlbumDto extends Media implements Serializable {
    String id;
    String artistId;
    int totalSongs;
}