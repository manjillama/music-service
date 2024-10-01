package edu.manjiltamang.music.controller;

import edu.manjiltamang.music.model.Album;
import edu.manjiltamang.music.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public void saveAlbum(@RequestBody Album album) {
        albumService.createAlbum(album);
    }
}