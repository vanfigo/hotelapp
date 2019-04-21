package com.bucketdev.hotelapp.service;

import com.bucketdev.hotelapp.dto.RoomReservationDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface RoomReservationService {

    List<RoomReservationDTO> findAll();
    List<RoomReservationDTO> findFromToPeople(Calendar from, Calendar to, int people);

}
