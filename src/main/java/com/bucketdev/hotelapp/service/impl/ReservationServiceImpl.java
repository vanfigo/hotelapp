package com.bucketdev.hotelapp.service.impl;

import com.bucketdev.hotelapp.domain.Reservation;
import com.bucketdev.hotelapp.domain.Room;
import com.bucketdev.hotelapp.dto.ReservationDTO;
import com.bucketdev.hotelapp.dto.RoomDTO;
import com.bucketdev.hotelapp.exception.bed.RoomNotFoundException;
import com.bucketdev.hotelapp.model.ReservationRepository;
import com.bucketdev.hotelapp.model.RoomRepository;
import com.bucketdev.hotelapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<ReservationDTO> findAll() {
        List<ReservationDTO> reservations = new ArrayList<>();
        for(Reservation reservation: this.repository.findAll()) {
            ReservationDTO dto = reservation.toDTO();
            dto.setRoom(reservation.getRoom().toDTO());
            reservations.add(dto);
        }
        return reservations;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        RoomDTO roomDTO = reservationDTO.getRoom();

        reservation.setValues(reservationDTO);
        reservation.setCost(roomDTO.getCost());

        Optional<Room> room = this.roomRepository.findById(roomDTO.getId());
        if(!room.isPresent())
            throw new RoomNotFoundException("id: " + roomDTO.getId());
        reservation.setRoom(room.get());
        return this.repository.save(reservation).toDTO();
    }
}
