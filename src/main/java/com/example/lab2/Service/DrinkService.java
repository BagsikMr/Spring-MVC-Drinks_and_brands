package com.example.lab2.Service;

import com.example.lab2.Class.*;
import com.example.lab2.Repository.BrandRepository;
import com.example.lab2.Repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository, BrandRepository brandRepository)
    {
        this.drinkRepository = drinkRepository;
        this.brandRepository = brandRepository;
    }

    public List<Drink> getAllDrinks()
    {
        return drinkRepository.findAll();
    }

    public Drink getDrinkById(UUID id){
        return drinkRepository.findById(id).orElse(null);
    }
    public Drink getDrinkByName(String name)
    {
        return drinkRepository.findByName(name);
    }

    public void createDrink(Drink drink){
        drinkRepository.save(drink);
    }

    public void updateDrink(UUID id, Drink updatedDrink)
    {
        if(drinkRepository.existsById(id))
        {
            updatedDrink.setId(id);
            drinkRepository.save(updatedDrink);
        }
    }

    public void updateDrink(String name, Drink updatedDrink)
    {
        UUID id = drinkRepository.findByName(name).getId();
        if(drinkRepository.existsById(id))
        {
            updatedDrink.setId(id);
            drinkRepository.save(updatedDrink);
        }
    }

    public void deleteDrink(UUID id)
    {
        Drink drink = drinkRepository.findById(id).orElse(null);
        if(drink!=null) {
            Brand brand = drink.getBrand();
            if (brand != null) {
                brand.getDrinks().remove(drink);
                brandRepository.save(brand);
            }
            drinkRepository.deleteById(id);
        }
    }

    public void deleteDrink(String name)
    {
        Drink drink = drinkRepository.findByName(name);
        if(drink!=null) {
            Brand brand = drink.getBrand();
            if (brand != null) {
                brand.getDrinks().remove(drink);
                brandRepository.save(brand);
            }
            drinkRepository.deleteById(drink.getId());
        }
    }

}
