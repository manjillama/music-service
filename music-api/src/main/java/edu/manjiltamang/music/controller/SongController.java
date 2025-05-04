package edu.manjiltamang.music.controller;

import edu.manjiltamang.music.dto.Artist;
import edu.manjiltamang.music.dto.Song;
import edu.manjiltamang.music.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
@Tag(
        name = "Songs",
        description = "Create, Retrieve, Update and Delete Songs"
)
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Song by ID",
            description = "REST API to search an Song by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status SUCCESS"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP status NOT FOUND"
            )
    })
    public ResponseEntity<Song> getSong(@PathVariable("id") String id) {
        return ResponseEntity
                .status(HttpStatus.OK).body(Song.from(songService.getSong(id)));
    }

    @PostMapping
    @Operation(
            summary = "Create Song",
            description = "REST API to create new Song"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP status CREATED"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP status NOT FOUND"
            )
    })
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Song.from(songService.createSong(Song.to(song))));
    }
}