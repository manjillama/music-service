package edu.manjiltamang.music.service;

import edu.manjiltamang.music.exceptions.NotFoundException;
import edu.manjiltamang.music.model.Album;
import edu.manjiltamang.music.repository.AlbumDAO;
import edu.manjiltamang.music.repository.ArtistDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlbumService {
    private final AlbumDAO albumDAO;
    private final ArtistDAO artistDAO;

    private static final Logger LOG = LoggerFactory.getLogger(AlbumService.class);

    @Autowired
    public AlbumService(AlbumDAO albumDAO, ArtistDAO artistDAO) {
        this.albumDAO = albumDAO;
        this.artistDAO = artistDAO;
    }

    public Album getAlbum(String albumId) {
        return albumDAO.getAlbum(albumId).orElseThrow(() -> new NotFoundException("Album", albumId));
    }

    public Album createAlbum(Album album) {
        album.setId(String.valueOf(UUID.randomUUID()));
        LOG.info("Creating album: {}", album);
        artistDAO.getArtist(album.getArtistId()).orElseThrow(() -> new NotFoundException("Artist", album.getArtistId()));
        return albumDAO.writeIfNotExists(album);
    }
}