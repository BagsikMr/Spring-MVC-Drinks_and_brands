package com.example.lab4drink.function;

import com.example.lab4drink.Class.Drink;
import com.example.lab4drink.DTO.BrandReadDTO;
import com.example.lab4drink.DTO.DrinkReadDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DrinkToResponseFunction implements Function<Drink, DrinkReadDTO> {

    public DrinkReadDTO apply(Drink entity) {
        return DrinkReadDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .price(entity.getPrice())
            .year(entity.getYear())
            .build();
    }
}
