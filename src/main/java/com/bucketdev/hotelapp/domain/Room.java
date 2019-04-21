package com.bucketdev.hotelapp.domain;

import com.bucketdev.hotelapp.dto.BedDTO;
import com.bucketdev.hotelapp.dto.RoomDTO;
import com.bucketdev.hotelapp.dto.RoomPhotoDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name = "room_beds",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "bed_id")
    )
    private List<Bed> beds;

    @Column
    private int number;

    @Column
    private float cost;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomPhoto> roomPhotos;

    public RoomDTO toDTO() {
        RoomDTO dto = new RoomDTO();
        List<BedDTO> bedsDTO = new ArrayList<>();
        for(Bed bed: beds) {
            bedsDTO.add(bed.toDTO());
        }
        List<RoomPhotoDTO> roomPhotosDTO = new ArrayList<>();
        for(RoomPhoto roomPhoto: roomPhotos) {
            roomPhotosDTO.add(roomPhoto.toDTO());
        }
        dto.setId(id);
        dto.setBeds(bedsDTO);
        dto.setRoomPhotos(roomPhotosDTO);
        dto.setNumber(number);
        dto.setCost(cost);
        return dto;
    }

    public void setValues(RoomDTO roomDTO) {
        this.id = roomDTO.getId();
        this.number = roomDTO.getNumber();
        this.cost = roomDTO.getCost();
        this.beds = new ArrayList<>();
        this.roomPhotos = new ArrayList<>();
    }
}
