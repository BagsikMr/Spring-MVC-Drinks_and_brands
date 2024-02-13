package com.example.lab4drink.function;

import com.example.lab4drink.Class.Brand;
import com.example.lab4drink.Class.Drink;
import com.example.lab4drink.DTO.DrinkCreateUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class PutRequestToDrinkFunction implements Function<DrinkCreateUpdateDTO, Drink> {
    public Drink apply(DrinkCreateUpdateDTO request) {
        return Drink.builder()
            .id(UUID.randomUUID())
            .brand(Brand.builder().id(request.getBrandId()).build())
            .name(request.getName())
            .year(request.getYear())
            .price(request.getPrice())
            .build();
    }
}
