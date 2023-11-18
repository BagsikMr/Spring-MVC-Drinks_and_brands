package com.example.lab4Brand.Class;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "drinks")
public class Drink implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private int price;
    @Column(name = "production_year")
    private String year;

    public String toString()
    {
        return "Drink: " + name + ", made by " + brand.getName() + " in " + year + ". ID: " + id;
    }

}
