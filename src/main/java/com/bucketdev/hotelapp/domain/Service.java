package com.bucketdev.hotelapp.domain;

import com.bucketdev.hotelapp.dto.ServiceDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    public ServiceDTO toDTO() {
        ServiceDTO dto = new ServiceDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

}
