package com.bucketdev.hotelapp.service.impl;

import com.bucketdev.hotelapp.domain.RoomReservation;
import com.bucketdev.hotelapp.dto.RoomReservationDTO;
import com.bucketdev.hotelapp.model.RoomReservationRepository;
import com.bucketdev.hotelapp.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class RoomReservationServiceImpl implements RoomReservationService {

    @Autowired
    private RoomReservationRepository repository;

    @Override
    public List<RoomReservationDTO> findAll() {
        List<RoomReservationDTO> roomReservations = new ArrayList<>();
        for(RoomReservation roomReservation: this.repository.findAll()) {
            roomReservations.add(roomReservation.toDTO());
        }
        return roomReservations;
    }

    @Override
    public List<RoomReservationDTO> findFromToPeople(Calendar from, Calendar to, int people) {
        List<RoomReservationDTO> roomReservations = new ArrayList<>();
        for(RoomReservation roomReservation: this.repository.findFromToPeople(from, to)) {
            RoomReservationDTO dto =  roomReservation.toDTO();
            if(people <= dto.getPeopleTotal())
                roomReservations.add(dto);
        }
        return roomReservations;
    }

}
