package com.bucketdev.hotelapp.exception.roomPhoto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomPhotoNotFoundException extends RuntimeException {

    public RoomPhotoNotFoundException(String message) {
        super(message);
    }


}
