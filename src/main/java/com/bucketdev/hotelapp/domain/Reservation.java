package com.bucketdev.hotelapp.domain;

import com.bucketdev.hotelapp.dto.ReservationDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar endDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column
    private int people;

    @Column
    private float cost;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    public enum Status {
        CREATED,
        CHECKED_IN,
        CHECKED_OUT;
    }

    public ReservationDTO toDTO() {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setPeople(people);
        dto.setCreationDate(creationDate);
        dto.setStatus(status);
        return dto;
    }

    public void setValues(ReservationDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.people = dto.getPeople();
        this.status = dto.getStatus();
    }

}
