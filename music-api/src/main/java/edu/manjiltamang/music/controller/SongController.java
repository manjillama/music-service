package edu.manjiltamang.music.controller;

import edu.manjiltamang.music.dto.Song;
import edu.manjiltamang.music.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void createSong(@RequestBody Song song) {
        songService.createSong(song);
    }
}