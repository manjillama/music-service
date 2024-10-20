package edu.manjiltamang.music.exceptions;

public class HttpErrorDetailsContainer implements HttpErrorDetails {

    private final String userMessage;
    private final String code;
    private final int status;
    private final String extraLogMessage;

    public HttpErrorDetailsContainer(int status, String code, String userMessage) {
        this(status, code, userMessage, null);
    }

    public HttpErrorDetailsContainer(int status, String code, String userMessage, String extraLogMessage) {
        this.status = status;
        this.code = code;
        this.userMessage = userMessage;
        this.extraLogMessage = extraLogMessage;
    }

    @Override
    public int getHttpStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getUserMessage() {
        return userMessage;
    }

    @Override
    public String getExtraLogMessage() {
        return extraLogMessage;
    }
}
