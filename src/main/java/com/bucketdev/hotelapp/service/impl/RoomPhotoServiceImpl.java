package com.bucketdev.hotelapp.service.impl;

import com.bucketdev.hotelapp.domain.RoomPhoto;
import com.bucketdev.hotelapp.dto.RoomPhotoDTO;
import com.bucketdev.hotelapp.model.RoomPhotoRepository;
import com.bucketdev.hotelapp.service.RoomPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomPhotoServiceImpl implements RoomPhotoService {

    @Autowired
    private RoomPhotoRepository repository;

    @Override
    public RoomPhotoDTO save(RoomPhotoDTO roomPhotoDTO) {
        RoomPhoto roomPhoto = new RoomPhoto();
        roomPhoto.setValues(roomPhotoDTO);

        return this.repository.save(roomPhoto).toDTO();
    }

}
