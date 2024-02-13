package com.example.lab4Brand.Function;

import com.example.lab4Brand.Class.Brand;
import com.example.lab4Brand.DTO.BrandCreateUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class PutRequestToBrandFunction implements Function<BrandCreateUpdateDTO, Brand> {

    public Brand apply(BrandCreateUpdateDTO request) {
        return Brand.builder()
            .id(UUID.randomUUID())
            .name(request.getName())
            .country(request.getCountry())
            .build();
    }

}
