package com.bucketdev.hotelapp.controller;

import com.bucketdev.hotelapp.dto.RoomDTO;
import com.bucketdev.hotelapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping()
    public ResponseEntity<List<RoomDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<RoomDTO> save(@RequestBody RoomDTO roomDTO) {
        return new ResponseEntity<>(this.service.save(roomDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        this.service.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
