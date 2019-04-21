package com.bucketdev.hotelapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BedDTO implements Serializable {

    private int id;
    private String name;
    private int people;
    private int width;
    private int height;

}
