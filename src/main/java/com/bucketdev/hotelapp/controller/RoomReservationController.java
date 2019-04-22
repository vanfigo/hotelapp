package com.bucketdev.hotelapp.controller;

import com.bucketdev.hotelapp.dto.RoomReservationDTO;
import com.bucketdev.hotelapp.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/roomReservations")
public class RoomReservationController {

    @Autowired
    private RoomReservationService service;

    @GetMapping
    public ResponseEntity<List<RoomReservationDTO>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{people}")
    public ResponseEntity<List<RoomReservationDTO>> findPeople(@PathVariable int people) {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/from/{from}/to/{to}/people/{people}")
    public ResponseEntity<List<RoomReservationDTO>> findFromToPeople(
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Calendar from,
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Calendar to,
            @PathVariable int people) {
        return new ResponseEntity<>(this.service.findFromToPeople(from, to, people), HttpStatus.OK);
    }

}
