package com.raytine.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MinioException extends RuntimeException{

    private final int code;

    public MinioException(String message, int code) {
        super(message);
        this.code = code;
    }
}
