package com.bucketdev.hotelapp.model;

import com.bucketdev.hotelapp.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r from Reservation r WHERE r.status IN (:statuses)")
    List<Reservation> findByStatus(List<Reservation.Status> statuses);

    List<Reservation> findByNameContaining(String name);

    List<Reservation> findByNameContainingAndEmail(String name, String email);

    List<Reservation> findByNameContainingAndEmailAndRoomNumber(String name, String email, int number);

    List<Reservation> findByNameContainingAndRoomNumber(String name, int number);

    List<Reservation> findByEmailAndRoomNumber(String email, int number);

    List<Reservation> findByEmail(String email);

    List<Reservation> RoomNumber(int number);
}
