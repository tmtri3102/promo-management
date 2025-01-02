package com.springbootcustomerprovince.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "provinces")
@Data
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
