package com.bucketdev.hotelapp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "room_beds")
public class RoomBed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Bed bed;

}
