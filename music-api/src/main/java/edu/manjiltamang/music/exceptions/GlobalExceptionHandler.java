package edu.manjiltamang.music.exceptions;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex, WebRequest request) {
        return buildErrorResponse(ex, null, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(@Nonnull Exception ex, @Nullable HttpErrorDetails details, @Nonnull WebRequest request) {
        if (details == null) {
            details = getHttpErrorDetails(ex, request);
        }

        Map<String, String> interestingProperties = extractInterestingProperties(ex);

        // Prepare the developer info
        ErrorResponse.DeveloperInfo developerInfo = ErrorResponse.DeveloperInfo.builder()
                .exceptionClass(ex.getClass().getName())
                .exceptionMessage(ex.getMessage())
                .thrownFrom(getThrownFrom(ex))
                .interestingExceptionProperties(interestingProperties)
                .build();

        // Prepare the error response
        ErrorResponse errorResponse = ErrorResponse.builder()
                .developerInfo(developerInfo)
                .status(details.getHttpStatus())
                .timestamp(System.currentTimeMillis())
                .path(request.getDescription(false).replace("uri=", ""))
                .errorCode(details.getCode())
                .code(details.getCode())
                .userMessage(details.getUserMessage() == null ? ex.getMessage() : details.getUserMessage()) // Default user message
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(details.getHttpStatus()));
    }

    private Map<String, String> extractInterestingProperties(Exception ex) {
        Map<String, String> properties = new HashMap<>();
        for (Method method : ex.getClass().getDeclaredMethods()) {
            if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                try {
                    Object value = method.invoke(ex);
                    if (value != null) {
                        properties.put(method.getDeclaringClass().getName() + "." + method.getName(), value.toString());
                    }
                } catch (Exception e) {
                    // Handle or log exceptions while reflecting
                }
            }
        }
        return properties;
    }

    private String getThrownFrom(Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement element = stackTrace[0];
            return element.getClassName() + " ... " + element.getMethodName() + "()" + ":" + element.getLineNumber();
        }
        return "Unknown location";
    }

    @Nonnull
    private HttpErrorDetails getHttpErrorDetails(Exception ex, WebRequest request) {
        //Genesys handled exceptions
        if (ex instanceof HttpErrorDetails) {
            return (HttpErrorDetails) ex;
        }

        return new HttpErrorDetailsContainer(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UNSPECIFIED_ERROR", "An unspecified error occurred");
    }
}