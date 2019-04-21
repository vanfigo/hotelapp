package com.bucketdev.hotelapp.domain;

import com.bucketdev.hotelapp.dto.BedDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "beds")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int people;

    @Column
    private int width;

    @Column
    private int height;

    public BedDTO toDTO() {
        BedDTO dto = new BedDTO();

        dto.setId(id);
        dto.setName(name);
        dto.setPeople(people);
        dto.setWidth(width);
        dto.setHeight(height);

        return dto;
    }

    public void setValues(BedDTO bedDTO) {
        this.id = bedDTO.getId();
        this.name = bedDTO.getName();
        this.people = bedDTO.getPeople();
        this.height = bedDTO.getHeight();
        this.width = bedDTO.getWidth();
    }
}
