package com.example.lab4Brand.Function;

import com.example.lab4Brand.Class.Brand;
import com.example.lab4Brand.DTO.BrandCreateUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateBrandWithRequestFunction implements BiFunction<Brand, BrandCreateUpdateDTO, Brand> {

    public Brand apply(Brand entity, BrandCreateUpdateDTO request) {
        return Brand.builder()
            .id(entity.getId())
            .name(request.getName())
            .country(request.getCountry())
            .build();
    }
}
