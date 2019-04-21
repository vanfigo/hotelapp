package com.bucketdev.hotelapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceDTO implements Serializable {

    private int id;
    private String name;
    private String description;

}
