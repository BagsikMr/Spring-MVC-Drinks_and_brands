package com.example.lab2.DTO;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DrinkReadDTO implements Serializable {
    private UUID id;
    private String name;
    private BrandReadDTO brand;
    private int price;
    private String year;
}

