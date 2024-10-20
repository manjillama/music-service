package edu.manjiltamang.music.service;

import edu.manjiltamang.music.model.Artist;
import edu.manjiltamang.music.repository.ArtistDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    private final ArtistDAO artistDAO;
    private static final Logger LOG = LoggerFactory.getLogger(ArtistService.class);

    @Autowired
    public ArtistService(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public Artist createArtist(Artist artist) {
        LOG.info("Creating artist with id: {} & name: {}", artist.getId(), artist.getName());
        return artistDAO.writeIfNotExists(artist);
    }
}