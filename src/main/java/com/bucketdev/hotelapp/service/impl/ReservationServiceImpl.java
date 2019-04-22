package com.bucketdev.hotelapp.service.impl;

import com.bucketdev.hotelapp.domain.Reservation;
import com.bucketdev.hotelapp.domain.Room;
import com.bucketdev.hotelapp.dto.ReservationDTO;
import com.bucketdev.hotelapp.dto.RoomDTO;
import com.bucketdev.hotelapp.exception.reservation.ReservationNotFoundException;
import com.bucketdev.hotelapp.exception.room.RoomNotFoundException;
import com.bucketdev.hotelapp.model.ReservationRepository;
import com.bucketdev.hotelapp.model.RoomRepository;
import com.bucketdev.hotelapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<ReservationDTO> findAll() {
        return this.convert(this.repository.findAll());
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        RoomDTO roomDTO = reservationDTO.getRoom();

        reservation.setValues(reservationDTO);
        reservation.setCost(roomDTO.getCost());

        Optional<Room> room = this.roomRepository.findById(roomDTO.getId());
        if(room.isEmpty())
            throw new RoomNotFoundException("id: " + roomDTO.getId());
        reservation.setRoom(room.get());
        return this.repository.save(reservation).toDTO();
    }

    @Override
    public ReservationDTO checkIn(long id) {
        Optional<Reservation> optionalReservation = this.repository.findById(id);
        if(optionalReservation.isEmpty())
            throw new ReservationNotFoundException("id: " + id);

        Reservation reservation = optionalReservation.get();
        reservation.setStatus(Reservation.Status.CHECKED_IN);
        reservation.setCheckInDate(Calendar.getInstance());
        this.repository.save(reservation);

        return reservation.toDTO();
    }

    @Override
    public ReservationDTO checkOut(long id) {
        Optional<Reservation> optionalReservation = this.repository.findById(id);
        if(optionalReservation.isEmpty())
            throw new ReservationNotFoundException("id: " + id);

        Reservation reservation = optionalReservation.get();
        reservation.setStatus(Reservation.Status.CHECKED_OUT);
        reservation.setCheckOutDate(Calendar.getInstance());
        this.repository.save(reservation);

        return reservation.toDTO();
    }

    @Override
    public List<ReservationDTO> filter(ReservationDTO dto) {
        if(!StringUtils.isEmpty(dto.getName())) {
            if(!StringUtils.isEmpty(dto.getEmail())) {
                if(dto.getRoom().getNumber() > 0) {
                    return this.convert(this.repository.findByNameContainingAndEmailAndRoomNumber(dto.getName(), dto.getEmail(), dto.getRoom().getNumber()));
                }
                return this.convert(this.repository.findByNameContainingAndEmail(dto.getName(), dto.getEmail()));
            }
            if(dto.getRoom().getNumber() > 0) {
                return this.convert(this.repository.findByNameContainingAndRoomNumber(dto.getName(), dto.getRoom().getNumber()));
            }
            return this.convert(this.repository.findByNameContaining(dto.getName()));
        }
        if(!StringUtils.isEmpty(dto.getEmail())) {
            if(dto.getRoom().getNumber() > 0) {
                return this.convert(this.repository.findByEmailAndRoomNumber(dto.getEmail(), dto.getRoom().getNumber()));
            }
            return this.convert(this.repository.findByEmail(dto.getEmail()));
        }
        if(dto.getRoom().getNumber() > 0) {
            return this.convert(this.repository.RoomNumber(dto.getRoom().getNumber()));
        }
        return this.convert(this.repository.findByStatus(Arrays.asList(Reservation.Status.CREATED, Reservation.Status.CHECKED_IN)));
    }

    private List<ReservationDTO> convert(List<Reservation> reservations) {
        List<ReservationDTO> reservationsDTO = new ArrayList<>();
        for(Reservation reservation: reservations) {
            ReservationDTO dto = reservation.toDTO();
            dto.setRoom(reservation.getRoom().toDTO());
            reservationsDTO.add(dto);
        }
        return reservationsDTO;
    }
}
