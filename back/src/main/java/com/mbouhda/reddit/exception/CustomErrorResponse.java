package com.mbouhda.reddit.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
}
