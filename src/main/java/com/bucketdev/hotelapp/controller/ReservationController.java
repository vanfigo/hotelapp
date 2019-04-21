package com.bucketdev.hotelapp.controller;

import com.bucketdev.hotelapp.dto.ReservationDTO;
import com.bucketdev.hotelapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<ReservationDTO> upsert(@RequestBody ReservationDTO dto) {
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.CREATED);
    }

}
