package com.bucketdev.hotelapp.dto;

import com.bucketdev.hotelapp.domain.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
public class ReservationDTO implements Serializable {

    private long id;
    private String name;
    private String lastName;
    private String email;
    @JsonFormat(timezone = "GMT-06:00")
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
    @JsonFormat(timezone = "GMT-06:00")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;
    @JsonFormat(timezone = "GMT-06:00")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private int people;
    private Reservation.Status status;
    private RoomDTO room;

}
