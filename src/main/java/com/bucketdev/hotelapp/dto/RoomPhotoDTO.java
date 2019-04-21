package com.bucketdev.hotelapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomPhotoDTO implements Serializable {

    private int id;
    private int roomId;
    private String url;

}
