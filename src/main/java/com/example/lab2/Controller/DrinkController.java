package com.example.lab2.Controller;


import com.example.lab2.Class.*;
import com.example.lab2.Service.*;
import  org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.lab2.DTO.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final BrandService brandService;

    @Autowired
    public DrinkController(DrinkService drinkService,BrandService brandService)
    {
        this.drinkService = drinkService;
        this.brandService = brandService;
    }


    @GetMapping("/{name}")
    public ResponseEntity<DrinkReadDTO> getDrinkByName(@PathVariable String name)
    {
        Drink drink = drinkService.getDrinkByName(name);
        if(drink != null)
        {
            return ResponseEntity.ok(mapToDrinkReadDTO(drink));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DrinkReadDTO>> getAllDrinks()
    {
        List<Drink> drinks = drinkService.getAllDrinks();
        List<DrinkReadDTO> drinkReadDTOs = drinks.stream()
                .map(this::mapToDrinkReadDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(drinkReadDTOs);
    }

    @PostMapping
    public ResponseEntity<DrinkReadDTO> createDrink(@RequestBody DrinkCreateUpdateDTO drinkDTO)
    {
        Brand brand = brandService.getBrandById(drinkDTO.getBrandId());
        if(brand == null)
        {
            return ResponseEntity.notFound().build();
        }


        Drink newDrink = mapToDrink(drinkDTO);
        newDrink.setBrand(brand);
        drinkService.createDrink(newDrink);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDrinkReadDTO(newDrink));
    }

    @PutMapping("/{name}")
    public ResponseEntity<DrinkReadDTO> updateDrink(@PathVariable String name, @RequestBody DrinkCreateUpdateDTO drinkDTO)
    {
        /*
        Brand brand = brandService.getBrandById(drinkDTO.getBrandId());
        if(brand == null)
        {
            return ResponseEntity.notFound().build();
        }
        */
        Drink existingDrink = drinkService.getDrinkByName(name);
        if(existingDrink == null)
        {
            return ResponseEntity.notFound().build();
        }

        Drink updatedDrink = mapToDrink(drinkDTO);
        updatedDrink.setId(existingDrink.getId());
        updatedDrink.setBrand(existingDrink.getBrand());
        drinkService.updateDrink(name,updatedDrink);
        return ResponseEntity.ok(mapToDrinkReadDTO(updatedDrink));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteDrink(@PathVariable String name)
    {
        drinkService.deleteDrink(name);
        return ResponseEntity.noContent().build();
    }

    private DrinkReadDTO mapToDrinkReadDTO(Drink drink)
    {
        return DrinkReadDTO.builder()
                .id(drink.getId())
                .name(drink.getName())
                .brand(mapToBrandReadDTO(drink.getBrand())) //TODO tutaj jest
                .price(drink.getPrice())
                .year(drink.getYear())
                .build();
    }
    private Drink mapToDrink(DrinkCreateUpdateDTO drinkDTO) {
        return Drink.builder()
                .name(drinkDTO.getName())
                .price(drinkDTO.getPrice())
                .year(drinkDTO.getYear())
                .build();
    }
    private BrandReadDTO mapToBrandReadDTO(Brand brand){  //TODO potem to podmień jak będziesz miał w Controlerze brandu chyba
        return BrandReadDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .country(brand.getCountry())
                .build();
    }

}
