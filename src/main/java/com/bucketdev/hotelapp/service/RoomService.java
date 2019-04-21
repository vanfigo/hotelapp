package com.bucketdev.hotelapp.service;

import com.bucketdev.hotelapp.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> findAll();
    RoomDTO findById(int id);
    RoomDTO save(RoomDTO roomDTO);
    void deleteById(int id);
}
