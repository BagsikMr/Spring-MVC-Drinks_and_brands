package com.example.lab4Brand.Class;

import lombok.*;
import jakarta.persistence.*;
import java.io.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "brands")
public class Brand implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    private String country;

    public Brand(String setName, String setCountry)
    {
        this.name = setName;
        this.country = setCountry;
    }
}
