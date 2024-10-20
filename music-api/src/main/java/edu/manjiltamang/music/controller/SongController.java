package edu.manjiltamang.music.controller;

import edu.manjiltamang.music.dto.SongDto;
import edu.manjiltamang.music.model.Song;
import edu.manjiltamang.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public void createSong(@RequestBody SongDto song) {
        songService.createSong(song);
    }
}