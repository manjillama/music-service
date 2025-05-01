package edu.manjiltamang.music.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Artist implements Serializable {
    String id;
    String name;
    long totalStreams;
    int debutYear;

    public static Artist from(edu.manjiltamang.music.model.Artist artist) {
        var artisDto = new Artist();
        artisDto.setName(artist.getName());
        artisDto.setDebutYear(artist.getDebutYear());
        artisDto.setTotalStreams(artist.getTotalStreams());
        return artisDto;
    }

    public static edu.manjiltamang.music.model.Artist to(Artist artistDto) {
        var artist = new edu.manjiltamang.music.model.Artist();
        artist.setName(artistDto.getName());
        artist.setDebutYear(artistDto.getDebutYear());
        artist.setTotalStreams(artistDto.getTotalStreams());
        return artist;
    }
}