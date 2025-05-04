package edu.manjiltamang.music.service;

import edu.manjiltamang.music.exceptions.NotFoundException;
import edu.manjiltamang.music.model.Album;
import edu.manjiltamang.music.model.Artist;
import edu.manjiltamang.music.model.Song;
import edu.manjiltamang.music.repository.AlbumDAO;
import edu.manjiltamang.music.repository.ArtistDAO;
import edu.manjiltamang.music.repository.SongDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SongService {
    private final SongDAO songDAO;
    private final AlbumDAO albumDAO;
    private final ArtistDAO artistDAO;

    private static final Logger LOG = LoggerFactory.getLogger(SongService.class);

    @Autowired
    public SongService(SongDAO songDAO, AlbumDAO albumDAO, ArtistDAO artistDAO) {
        this.songDAO = songDAO;
        this.albumDAO = albumDAO;
        this.artistDAO = artistDAO;
    }

    public Song getSong(String songId) {
        return songDAO.getSong(songId).orElseThrow(() -> new NotFoundException("Song", songId));
    }

    public Song createSong(Song song) {
        song.setId(String.valueOf(UUID.randomUUID()));
        LOG.info("Creating song: {}", song);

        Album album = albumDAO.getAlbum(song.getAlbumId()).orElseThrow(() -> new NotFoundException("Album", song.getAlbumId()));
        song.setAlbum(album);

        artistDAO.getArtist(album.getArtistId()).orElseThrow(() -> new NotFoundException("Artist", song.getArtistId()));

        return songDAO.writeIfNotExists(song);
    }
}