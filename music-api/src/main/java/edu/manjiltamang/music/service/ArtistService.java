package edu.manjiltamang.music.service;

import edu.manjiltamang.music.exceptions.NotFoundException;
import edu.manjiltamang.music.model.Artist;
import edu.manjiltamang.music.repository.ArtistDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ArtistService {
    private final ArtistDAO artistDAO;
    private static final Logger LOG = LoggerFactory.getLogger(ArtistService.class);

    @Autowired
    public ArtistService(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public Artist getArtist(String artistId) {
        return artistDAO.getArtist(artistId).orElseThrow(() -> new NotFoundException("Artist", artistId));
    }

    public Artist createArtist(Artist artist) {
        artist.setId(String.valueOf(UUID.randomUUID()));
        LOG.info("Creating artist: {}", artist);
        return artistDAO.writeIfNotExists(artist);
    }
}