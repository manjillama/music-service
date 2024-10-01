package edu.manjiltamang.music.model;

import lombok.Data;

@Data
public abstract class Media {
    private String title;
    private int releaseYear;
    private String genre;
}
