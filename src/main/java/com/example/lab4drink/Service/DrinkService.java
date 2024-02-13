package com.example.lab4drink.Service;

import com.example.lab4drink.Class.*;
import com.example.lab4drink.Repository.BrandRepository;
import com.example.lab4drink.Repository.DrinkRepository;
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

    public Optional<List<Drink>> getAllByBrand(UUID id){return brandRepository.findById(id).map(drinkRepository::findByBrand);}

    public Optional<Drink> getDrinkById(UUID id){
        return drinkRepository.findById(id);
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
    public void updateDrink(Drink newDrink)
    {
        UUID id = newDrink.getId();
        Optional<Drink> tempDrink = drinkRepository.findById(id);

        if(tempDrink.isPresent()) {
            Drink oldDrink = tempDrink.get();

            oldDrink.setName(newDrink.getName());
            oldDrink.setPrice(newDrink.getPrice());
            oldDrink.setYear(newDrink.getYear());

            drinkRepository.save(oldDrink);
        }
        else
        {
            System.out.println("Error 404 drink not found xd");
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

}
