package com.bucketdev.hotelapp.domain;

import com.bucketdev.hotelapp.dto.RoomPhotoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "room_photos")
public class RoomPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Room room;

    @Column
    private String url;

    public RoomPhotoDTO toDTO() {
        RoomPhotoDTO dto = new RoomPhotoDTO();
        dto.setId(id);
        dto.setUrl(url);
        return dto;
    }

    public void setValues(RoomPhotoDTO roomPhotoDTO) {
        this.id = roomPhotoDTO.getId();
        this.url = roomPhotoDTO.getUrl();
    }
}
