package com.cognity.temperatures.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class Cities {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "temperature", nullable = false)
    private String temperature;

    @Column(name = "humidity")
    private String humidity;
}
