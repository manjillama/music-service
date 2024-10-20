package edu.manjiltamang.music.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private DeveloperInfo developerInfo;
    private int status;
    private long timestamp;
    private String path;
    private String errorCode;
    private String userMessage;
    private String message;
    private String code;

    @Data
    @Builder
    public static class DeveloperInfo {
        private String exceptionClass;
        private String exceptionMessage;
        private String thrownFrom;
        private Map<String, String> interestingExceptionProperties;
    }
}