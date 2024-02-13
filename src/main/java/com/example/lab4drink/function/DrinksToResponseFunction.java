package com.example.lab4drink.function;

import com.example.lab4drink.Class.Drink;
import com.example.lab4drink.DTO.DrinksReadDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class DrinksToResponseFunction implements Function<List<Drink>, DrinksReadDTO> {

    public DrinksReadDTO apply(List<Drink> entities) {
        return DrinksReadDTO.builder()
            .drinks(entities.stream()
                .map(drink -> DrinksReadDTO.Drink.builder()
                    .id(drink.getId())
                    .name(drink.getName())
                    .build())
                .toList())
            .build();
    }

}
