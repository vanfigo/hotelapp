package com.bucketdev.hotelapp.model;

import com.bucketdev.hotelapp.domain.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {

    @Query("SELECT DISTINCT rr FROM RoomReservation rr " +
            "LEFT JOIN FETCH rr.reservations r " +
            "WHERE (r IS NULL OR r.startDate <> CURRENT_DATE)")
    List<RoomReservation> findAll();

    @Query("SELECT r " +
            "FROM RoomReservation r " +
            "WHERE r.id NOT IN (" +
            "SELECT DISTINCT r.room.id " +
            "FROM Reservation r " +
            "WHERE r.startDate BETWEEN :from AND :to " +
            "OR :from BETWEEN r.startDate AND r.endDate)")
    List<RoomReservation> findFromToPeople(Calendar from, Calendar to);

}
