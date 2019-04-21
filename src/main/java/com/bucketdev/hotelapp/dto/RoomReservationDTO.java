package com.bucketdev.hotelapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomReservationDTO {

    private int id;
    private int number;
    private float cost;
    private int peopleTotal;
    private List<RoomPhotoDTO> roomPhotos;
    private List<ReservationDTO> reservations;

}
