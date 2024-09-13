package com.insta.backend.exception;

import java.time.LocalDateTime;

public  class InstaErrorResponse{
    private int status;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    // Constructor, getters, and setters
    public InstaErrorResponse(int status, String message, String details, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    // Getters and setters omitted for brevity
}
