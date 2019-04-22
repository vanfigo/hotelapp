package com.bucketdev.hotelapp.service.impl;

import com.bucketdev.hotelapp.domain.Bed;
import com.bucketdev.hotelapp.domain.Room;
import com.bucketdev.hotelapp.domain.RoomPhoto;
import com.bucketdev.hotelapp.dto.BedDTO;
import com.bucketdev.hotelapp.dto.RoomDTO;
import com.bucketdev.hotelapp.dto.RoomPhotoDTO;
import com.bucketdev.hotelapp.exception.bed.BedNotFoundException;
import com.bucketdev.hotelapp.exception.room.RoomNotFoundException;
import com.bucketdev.hotelapp.exception.roomPhoto.RoomPhotoNotFoundException;
import com.bucketdev.hotelapp.model.BedRepository;
import com.bucketdev.hotelapp.model.RoomPhotoRepository;
import com.bucketdev.hotelapp.model.RoomRepository;
import com.bucketdev.hotelapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private RoomPhotoRepository roomPhotoRepository;

    @Override
    public List<RoomDTO> findAll() {
        List<RoomDTO> rooms = new ArrayList<>();
        for(Room room: repository.findAll())
            rooms.add(room.toDTO());
        return rooms;
    }

    @Override
    public RoomDTO findById(int id) {
        Optional<Room> room = this.repository.findById(id);
        if(!room.isPresent())
            throw new RoomNotFoundException("id: " + id);
        return room.get().toDTO();
    }

    @Override
    public RoomDTO save(RoomDTO roomDTO) {
        Room room = new Room();
        room.setValues(roomDTO);
        if(roomDTO.getId() > 0) {
            Optional<Room> optionalRoom = this.repository.findById(roomDTO.getId());
            if(!optionalRoom.isPresent()) {
                throw new RoomNotFoundException("id: " + roomDTO.getId());
            }
            room = optionalRoom.get();
        }
        room.getBeds().clear();
        room.getRoomPhotos().clear();
        for(BedDTO bedDTO: roomDTO.getBeds()) {
            Optional<Bed> bed = bedRepository.findById(bedDTO.getId());
            if(!bed.isPresent())
                throw new BedNotFoundException("id: " + bedDTO.getId());
            room.getBeds().add(bed.get());
        }
        for(RoomPhotoDTO roomPhotoDTO: roomDTO.getRoomPhotos()) {
            RoomPhoto roomPhoto = new RoomPhoto();
            if(roomPhotoDTO.getId() > 0) {
                Optional<RoomPhoto> optionalRoomPhoto = roomPhotoRepository.findById(roomPhotoDTO.getId());
                if (!optionalRoomPhoto.isPresent())
                    throw new RoomPhotoNotFoundException("id: " + roomPhotoDTO.getId());
                roomPhoto = optionalRoomPhoto.get();
            }
            roomPhoto.setValues(roomPhotoDTO);
            roomPhoto.setRoom(room);
            room.getRoomPhotos().add(roomPhoto);
        }
        return this.repository.save(room).toDTO();
    }

    @Override
    public void deleteById(int id) {
        this.repository.deleteById(id);
    }
}
