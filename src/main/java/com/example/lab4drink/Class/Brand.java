package com.example.lab4drink.Class;

import lombok.*;
import jakarta.persistence.*;
import java.io.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "brands")
public class Brand implements Serializable{

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drink> drinks;

}
