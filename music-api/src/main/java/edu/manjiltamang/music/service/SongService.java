package edu.manjiltamang.music.service;

import edu.manjiltamang.music.dto.Song;
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

    public edu.manjiltamang.music.model.Song createSong(Song songDto) {
        LOG.info("Creating song with id: {} & artist id: {}", songDto.getId(), songDto.getArtistId());

        edu.manjiltamang.music.model.Song song = new edu.manjiltamang.music.model.Song();
        song.setId(songDto.getId());
        song.setArtistId(songDto.getArtistId());
        song.setArtistName(songDto.getArtistName());
        song.setLyrics(songDto.getLyrics());
        song.setGenre(songDto.getGenre());
        song.setReleaseYear(songDto.getReleaseYear());
        song.setTitle(songDto.getTitle());
        song.setTotalStreams(0);

        // Todo: set album

        return songDAO.writeIfNotExists(song);
    }
}