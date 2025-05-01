package edu.manjiltamang.music.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Value
public class Song extends Media implements Serializable {
    String id;
    String artistId;
    String artistName;
    String albumId;
    String albumTitle;
    String lyrics;
    long totalStreams;
}