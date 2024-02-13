package com.example.lab4Brand.Function;

import org.springframework.stereotype.Component;
import com.example.lab4Brand.DTO.BrandsReadDTO;
import com.example.lab4Brand.Class.Brand;
import java.util.List;
import java.util.function.Function;

@Component
public class BrandsToResponseFunction implements Function<List<Brand>, BrandsReadDTO> {

    public BrandsReadDTO apply(List<Brand> entities) {
        return BrandsReadDTO.builder()
            .brands(entities.stream()
                .map(brand -> BrandsReadDTO.Brand.builder()
                    .id(brand.getId())
                    .name(brand.getName())
                    .build())
                .toList())
            .build();
    }
}
