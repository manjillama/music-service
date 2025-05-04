package edu.manjiltamang.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.manjiltamang.music.model.Album;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Song extends Media implements Serializable {
    String id;
    String artistId;
    String albumId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Album album;
    String lyrics;
    long totalStreams;

    public static Song from(edu.manjiltamang.music.model.Song song) {
        var songDto = new Song();
        songDto.setId(song.getId());
        songDto.setArtistId(song.getArtistId());
        songDto.setAlbumId(song.getAlbumId());
        songDto.setAlbum(song.getAlbum());
        songDto.setLyrics(song.getLyrics());
        songDto.setTotalStreams(song.getTotalStreams());
        songDto.setTitle(song.getTitle());
        songDto.setReleaseYear(song.getReleaseYear());
        songDto.setGenre(song.getGenre());
        return songDto;
    }

    public static edu.manjiltamang.music.model.Song to(Song songDto) {
        var song = new edu.manjiltamang.music.model.Song();
        song.setArtistId(songDto.getArtistId());
        song.setAlbumId(songDto.getAlbumId());
        song.setAlbum(songDto.getAlbum());
        song.setLyrics(songDto.getLyrics());
        song.setTotalStreams(songDto.getTotalStreams());
        song.setTitle(songDto.getTitle());
        song.setReleaseYear(songDto.getReleaseYear());
        song.setGenre(songDto.getGenre());
        return song;
    }
}