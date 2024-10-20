package edu.manjiltamang.music.service;

import edu.manjiltamang.music.dto.AlbumDto;
import edu.manjiltamang.music.exceptions.NotFoundException;
import edu.manjiltamang.music.model.Album;
import edu.manjiltamang.music.model.Artist;
import edu.manjiltamang.music.repository.AlbumDAO;
import edu.manjiltamang.music.repository.ArtistDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Album createAlbum(AlbumDto albumDto) {
        LOG.info("Creating album with id: {}", albumDto.getId());
        Artist artist = artistDAO.getArtist(albumDto.getArtistId()).orElseThrow(() -> new NotFoundException("Artist", albumDto.getArtistId()));

        Album album = new Album();
        album.setId(albumDto.getId());
        album.setTotalSongs(0);
        album.setTitle(albumDto.getTitle());
        album.setGenre(albumDto.getGenre());
        album.setReleaseYear(albumDto.getReleaseYear());

        album.setArtistId(artist.getId());
        album.setArtistName(artist.getName());

        return albumDAO.writeIfNotExists(album);
    }
}