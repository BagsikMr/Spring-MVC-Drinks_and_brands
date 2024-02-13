package com.example.lab4drink.function;

import com.example.lab4drink.Class.Drink;
import com.example.lab4drink.DTO.DrinkCreateUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateDrinkWithRequestFunction implements BiFunction<Drink, DrinkCreateUpdateDTO, Drink> {

    public Drink apply(Drink entity, DrinkCreateUpdateDTO request) {
        return Drink.builder()
            .id(entity.getId())
            .name(request.getName())
            .year(request.getYear())
            .price(request.getPrice())
            .build();
    }
}
