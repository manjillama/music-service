package edu.manjiltamang.music.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends RuntimeException implements HttpErrorDetails {

    //"The #{ex.getType()} you were searching for was not found \\(field #{ex.getFieldName()}, value #{ex.getFieldValue()}\\)"
    private static final String USER_MESSAGE = "The %s you were searching for was not found (field %s, value %s)";

    private String type;
    private String fieldName;
    private String fieldValue;
    private final String userMessage;

    public NotFoundException(String type, String fieldValue) {
        this(type, fieldValue, "id");
    }

    public NotFoundException(String type, String fieldValue, String fieldName) {
        super(String.format("Entity [%s] not found by [%s] [%s]", type, fieldName, fieldValue));
        this.type = type;
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;
        this.userMessage = String.format(USER_MESSAGE, type, fieldName, fieldValue);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String getCode() {
        return "entity.not.found";
    }

    @Override
    public String getUserMessage() {
        return userMessage;
    }
}
