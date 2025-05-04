package edu.manjiltamang.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Artist implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String id;
    String name;
    long totalStreams;
    int debutYear;

    public static Artist from(edu.manjiltamang.music.model.Artist artist) {
        var artistDto = new Artist();
        artistDto.setId(artist.getId());
        artistDto.setName(artist.getName());
        artistDto.setDebutYear(artist.getDebutYear());
        artistDto.setTotalStreams(artist.getTotalStreams());
        return artistDto;
    }

    public static edu.manjiltamang.music.model.Artist to(Artist artistDto) {
        var artist = new edu.manjiltamang.music.model.Artist();
        artist.setName(artistDto.getName());
        artist.setDebutYear(artistDto.getDebutYear());
        artist.setTotalStreams(artistDto.getTotalStreams());
        return artist;
    }
}