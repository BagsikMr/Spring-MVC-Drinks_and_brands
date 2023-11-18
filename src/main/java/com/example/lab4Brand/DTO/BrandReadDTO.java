package com.example.lab4Brand.DTO;

import lombok.*;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandReadDTO implements Serializable {
    private UUID id;
    private String name;
    private String country;
}
