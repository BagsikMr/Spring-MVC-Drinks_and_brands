package com.example.lab2.Repository;

import com.example.lab2.Class.Brand;
import com.example.lab2.Class.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, UUID> {
    List<Drink> findByBrand(Brand brand);
    Drink findByName(String name);
    void deleteDrinkByName(String name);
}