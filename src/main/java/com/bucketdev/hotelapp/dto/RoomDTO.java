package com.bucketdev.hotelapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoomDTO implements Serializable {
    private int id;
    private List<BedDTO> beds;
    private int number;
    private List<RoomPhotoDTO> roomPhotos;
    private float cost;
}
