package com.bucketdev.hotelapp.service;

import com.bucketdev.hotelapp.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    ReservationDTO save(ReservationDTO reservationDTO);

    List<ReservationDTO> findAll();
}
