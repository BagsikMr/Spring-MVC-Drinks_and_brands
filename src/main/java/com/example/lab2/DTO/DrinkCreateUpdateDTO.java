package com.example.lab2.DTO;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DrinkCreateUpdateDTO implements Serializable {
    private String name;
    private UUID brandId;
    private int price;
    private String year;
}

