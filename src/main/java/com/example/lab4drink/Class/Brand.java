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
    private UUID id;

    @ToString.Exclude
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Drink> drinks;

}
