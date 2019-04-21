package com.bucketdev.hotelapp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation_services")
public class ReservationService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Service service;

}
