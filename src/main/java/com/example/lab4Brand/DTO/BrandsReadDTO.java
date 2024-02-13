package com.example.lab4Brand.DTO;

import lombok.*;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandsReadDTO{

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Brand {

        private UUID id;

        private String name;

    }

    private List<Brand> brands;
}