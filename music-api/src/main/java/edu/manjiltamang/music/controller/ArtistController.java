package edu.manjiltamang.music.controller;

import edu.manjiltamang.music.dto.Artist;
import edu.manjiltamang.music.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artists")
@Tag(
        name = "Artists",
        description = "Create, Retrieve, Update and Delete Artists"
)
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Artist by ID",
            description = "REST API to search an Artist by id"
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
    public ResponseEntity<Artist> getArtist(@PathVariable("id") String id) {
        return ResponseEntity
                .status(HttpStatus.OK).body(Artist.from(artistService.getArtist(id)));
    }

    @PostMapping
    @Operation(
            summary = "Create Artist",
            description = "REST API to create new Artist"
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
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Artist.from(artistService.createArtist(Artist.to(artist))));
    }
}