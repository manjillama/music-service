package edu.manjiltamang.music.service;

import edu.manjiltamang.music.model.Album;
import edu.manjiltamang.music.repository.AlbumDAO;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO){
        this.albumDAO = albumDAO;
    }

    public Album createAlbum(Album album){
        return albumDAO.write(album);
    }
}