package edu.manjiltamang.music.exceptions;

public interface HttpErrorDetails {
    int getHttpStatus();

    String getCode();

    default String getUserMessage() {
        return null;
    }

    default String getExtraLogMessage() {
        return null;
    }
}
