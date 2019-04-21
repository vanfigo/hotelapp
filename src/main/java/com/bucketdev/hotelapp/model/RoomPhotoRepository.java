package com.bucketdev.hotelapp.model;

import com.bucketdev.hotelapp.domain.RoomPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPhotoRepository extends JpaRepository<RoomPhoto, Integer> {
}
