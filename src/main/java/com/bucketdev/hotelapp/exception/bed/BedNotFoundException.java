package com.bucketdev.hotelapp.exception.bed;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BedNotFoundException extends RuntimeException {

    public BedNotFoundException(String message) {
        super(message);
    }
}
