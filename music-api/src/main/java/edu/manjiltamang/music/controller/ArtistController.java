package edu.manjiltamang.music.controller;

import edu.manjiltamang.music.exceptions.NotFoundException;
import edu.manjiltamang.music.model.Artist;
import edu.manjiltamang.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtist(@PathVariable("id") String id) {
        throw new NotFoundException("contact", id);
//        return ResponseEntity
//                .status(HttpStatus.OK).body(null);
    }

    @PostMapping
    public void createArtist(@RequestBody Artist artist) {
        // TODO create artist DTO and return created artist
        artistService.createArtist(artist);
    }
}