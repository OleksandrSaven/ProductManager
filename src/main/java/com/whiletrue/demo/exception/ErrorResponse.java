package com.whiletrue.demo.exception;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public final class ErrorResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private List<String> errors;

    public ErrorResponse(LocalDateTime time, HttpStatus httpStatus, List<String> error) {
        this.timestamp = time;
        this.status = httpStatus;
        this.errors = error;
    }
}
