package com.bucketdev.hotelapp.service.impl;

import com.bucketdev.hotelapp.domain.Bed;
import com.bucketdev.hotelapp.dto.BedDTO;
import com.bucketdev.hotelapp.exception.bed.BedNotFoundException;
import com.bucketdev.hotelapp.model.BedRepository;
import com.bucketdev.hotelapp.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BedServiceImpl implements BedService {

    @Autowired
    private BedRepository repository;

    @Override
    public List<BedDTO> findAll() {
        List<BedDTO> beds = new ArrayList<>();
        for(Bed bed: this.repository.findAll())
            beds.add(bed.toDTO());
        return beds;
    }

    @Override
    public BedDTO findById(int id) {
        Optional<Bed> bed = this.repository.findById(id);
        if(!bed.isPresent())
            throw new BedNotFoundException("id: " + id);
        return bed.get().toDTO();
    }

    @Override
    public BedDTO save(BedDTO bedDTO) {
        Bed bed = new Bed();
        bed.setValues(bedDTO);

        return this.repository.save(bed).toDTO();
    }

    @Override
    public void deleteById(int id) {
        this.repository.deleteById(id);
    }

}
