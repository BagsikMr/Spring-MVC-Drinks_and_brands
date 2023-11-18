package com.example.lab4Brand.DTO;

import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandCreateUpdateDTO implements Serializable {
    private String name;
    private String country;
}

