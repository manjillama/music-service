package edu.manjiltamang.music.controller;

import edu.manjiltamang.music.dto.AlbumDto;
import edu.manjiltamang.music.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
@Tag(
        name = "Albums",
        description = "Create, Retrieve, Update and Delete Albums"
)
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    @Operation(
            summary = "Create Album",
            description = "REST API to create new Album"
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
    public ResponseEntity<?> createAlbum(@RequestBody AlbumDto album) {
        albumService.createAlbum(album);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(null);
    }
}