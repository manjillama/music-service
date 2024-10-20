package edu.manjiltamang.music.service;

import edu.manjiltamang.music.dto.SongDto;
import edu.manjiltamang.music.model.AlbumInfo;
import edu.manjiltamang.music.model.Song;
import edu.manjiltamang.music.repository.SongDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongDAO songDAO;
    private static final Logger LOG = LoggerFactory.getLogger(SongService.class);

    @Autowired
    public SongService(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public Song createSong(SongDto songDto) {
        LOG.info("Creating song with id: {} & artist id: {}", songDto.getId(), songDto.getArtistId());

        Song song = new Song();
        song.setId(songDto.getId());
        song.setArtistId(songDto.getArtistId());
        song.setArtistName(songDto.getArtistName());
        song.setLyrics(songDto.getLyrics());
        song.setGenre(songDto.getGenre());
        song.setReleaseYear(songDto.getReleaseYear());
        song.setTitle(songDto.getTitle());
        song.setTotalStreams(0);

        AlbumInfo albumInfo = new AlbumInfo();
        albumInfo.setId(songDto.getAlbumId());
        albumInfo.setTitle(songDto.getAlbumTitle());
        song.setAlbum(albumInfo);

        return songDAO.writeIfNotExists(song);
    }
}