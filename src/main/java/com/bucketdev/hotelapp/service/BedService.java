package com.bucketdev.hotelapp.service;

import com.bucketdev.hotelapp.dto.BedDTO;

import java.util.List;

public interface BedService {

    List<BedDTO> findAll();
    BedDTO findById(int id);
    BedDTO save(BedDTO bedDTO);
    void deleteById(int id);

}
