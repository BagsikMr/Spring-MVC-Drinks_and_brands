package com.example.lab4drink.Service;

import com.example.lab4drink.Class.*;
import com.example.lab4drink.Repository.BrandRepository;
import com.example.lab4drink.Repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final DrinkRepository drinkRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository, DrinkRepository drinkRepository) {
        this.brandRepository = brandRepository;
        this.drinkRepository = drinkRepository;
    }

    public Brand getBrandById(UUID id) {
        return brandRepository.findById(id).orElse(null);
    }
    public Brand getBrandByName(String name)
    {
        return brandRepository.findByName(name);
    }

    public void createBrand(Brand brand) {
        brandRepository.save(brand);
    }


    public void deleteBrand(UUID id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand != null) {
            List<Drink> drinks = brand.getDrinks();
            if (drinks != null) {
                drinkRepository.deleteAll(drinks);
            }
            brandRepository.deleteById(id);
        }
    }
    public void deleteBrand(String name) {
        Brand brand = brandRepository.findByName(name);
        if (brand != null) {
            List<Drink> drinks = brand.getDrinks();
            if (drinks != null) {
                drinkRepository.deleteAll(drinks);
            }
            brandRepository.deleteById(brand.getId());
        }
    }


}

