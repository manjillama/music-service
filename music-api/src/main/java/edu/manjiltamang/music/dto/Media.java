package edu.manjiltamang.music.dto;

import lombok.Data;

@Data
public abstract class Media {
    private String title;
    private int releaseYear;
    private String genre;
}
