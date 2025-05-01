package edu.manjiltamang.music.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Value
public class Album extends Media implements Serializable {
    String id;
    String artistId;
    String artistName;
    int totalSongs;
}