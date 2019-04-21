package com.bucketdev.hotelapp.controller;

import com.bucketdev.hotelapp.dto.RoomPhotoDTO;
import com.bucketdev.hotelapp.service.RoomPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roomPhotos")
public class RoomPhotoController {

    @Autowired
    private RoomPhotoService service;

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<RoomPhotoDTO> save(@RequestBody RoomPhotoDTO roomPhotoDTO) {
        return new ResponseEntity<>(this.service.save(roomPhotoDTO), HttpStatus.CREATED);
    }

}
