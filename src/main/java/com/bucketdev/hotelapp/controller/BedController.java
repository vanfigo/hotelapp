package com.bucketdev.hotelapp.controller;

import com.bucketdev.hotelapp.dto.BedDTO;
import com.bucketdev.hotelapp.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beds")
public class BedController {

    @Autowired
    private BedService service;

    @GetMapping()
    public ResponseEntity<List<BedDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BedDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<BedDTO> save(@RequestBody BedDTO bedDTO) {
        return new ResponseEntity<>(this.service.save(bedDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        this.service.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
