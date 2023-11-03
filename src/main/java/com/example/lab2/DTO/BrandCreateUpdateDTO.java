package com.example.lab2.DTO;

import com.example.lab2.Class.Brand;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandCreateUpdateDTO implements Serializable {
    private String name;
    private String country;
}

