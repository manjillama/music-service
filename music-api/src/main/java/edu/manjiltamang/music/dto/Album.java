package edu.manjiltamang.music.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Album extends Media implements Serializable {
    String id;
    String artistId;
    int totalSongs;

    public static Album from(edu.manjiltamang.music.model.Album album) {
        var albumDto = new Album();
        albumDto.setId(album.getId());
        albumDto.setArtistId(album.getArtistId());
        albumDto.setTotalSongs(album.getTotalSongs());
        albumDto.setTitle(album.getTitle());
        albumDto.setReleaseYear(album.getReleaseYear());
        albumDto.setGenre(album.getGenre());
        return albumDto;
    }

    public static edu.manjiltamang.music.model.Album to(Album albumDto) {
        var album = new edu.manjiltamang.music.model.Album();
        album.setArtistId(albumDto.getArtistId());
        album.setTotalSongs(albumDto.getTotalSongs());
        album.setTitle(albumDto.getTitle());
        album.setReleaseYear(albumDto.getReleaseYear());
        album.setGenre(albumDto.getGenre());
        return album;
    }
}