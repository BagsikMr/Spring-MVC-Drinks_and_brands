package com.example.lab4drink.DTO;

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

