package com.example.lab4drink.DTO;

import com.example.lab4drink.Class.Drink;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DrinksReadDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Drink {

        private UUID id;

        private String name;

    }

    private List<Drink> drinks;

}
