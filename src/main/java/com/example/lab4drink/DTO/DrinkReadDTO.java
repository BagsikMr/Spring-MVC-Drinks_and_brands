package com.example.lab4drink.DTO;

import com.example.lab4drink.Class.Brand;
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
    private Brand brand;
    private int price;
    private String year;
}

