package com.bucketdev.hotelapp.domain;

import com.bucketdev.hotelapp.dto.ReservationDTO;
import com.bucketdev.hotelapp.dto.RoomPhotoDTO;
import com.bucketdev.hotelapp.dto.RoomReservationDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int number;

    @Column
    private float cost;

    @ManyToMany
    @JoinTable(name = "room_beds",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "bed_id")
    )
    private List<Bed> beds;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomPhoto> roomPhotos;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    public RoomReservationDTO toDTO() {
        RoomReservationDTO dto = new RoomReservationDTO();
        dto.setId(id);
        dto.setNumber(number);
        dto.setCost(cost);
        dto.setPeopleTotal(beds.stream().mapToInt(Bed::getPeople).sum());
        List<RoomPhotoDTO> roomPhotosDTO = new ArrayList<>();
        for(RoomPhoto roomPhoto: roomPhotos) {
            roomPhotosDTO.add(roomPhoto.toDTO());
        }
        dto.setRoomPhotos(roomPhotosDTO);
        List<ReservationDTO> reservationsDTO = new ArrayList<>();
        for(Reservation reservation: reservations) {
            reservationsDTO.add(reservation.toDTO());
        }
        dto.setReservations(reservationsDTO);
        return dto;
    }

}
