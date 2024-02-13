package com.example.lab4Brand.Function;

import com.example.lab4Brand.Class.Brand;
import com.example.lab4Brand.DTO.BrandReadDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BrandToResponseFunction implements Function<Brand, BrandReadDTO> {

    public BrandReadDTO apply(Brand entity) {
        return BrandReadDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .country(entity.getCountry())
            .build();
    }
}
