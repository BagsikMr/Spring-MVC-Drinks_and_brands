package com.example.lab4drink.Controller;


import com.example.lab4drink.Class.*;
import com.example.lab4drink.Service.*;
import com.example.lab4drink.function.DrinkToResponseFunction;
import com.example.lab4drink.function.DrinksToResponseFunction;
import com.example.lab4drink.function.PutRequestToDrinkFunction;
import com.example.lab4drink.function.UpdateDrinkWithRequestFunction;
import  org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.lab4drink.DTO.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final BrandService brandService;
    private final DrinksToResponseFunction drinksToResponseFunction;
    private final DrinkToResponseFunction drinkToResponseFunction;
    private final PutRequestToDrinkFunction putRequestToDrinkFunction;
    private final UpdateDrinkWithRequestFunction updateDrinkWithRequestFunction;

    @Autowired
    public DrinkController(
            DrinkService drinkService,
            BrandService brandService,
            DrinksToResponseFunction drinksToResponseFunction,
            DrinkToResponseFunction drinkToResponseFunction,
            PutRequestToDrinkFunction putRequestToDrinkFunction,
            UpdateDrinkWithRequestFunction updateDrinkWithRequestFunction
            )
    {
        this.drinkService = drinkService;
        this.brandService = brandService;
        this.drinksToResponseFunction = drinksToResponseFunction;
        this.drinkToResponseFunction = drinkToResponseFunction;
        this.putRequestToDrinkFunction = putRequestToDrinkFunction;
        this.updateDrinkWithRequestFunction = updateDrinkWithRequestFunction;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrinkReadDTO> getDrinkByName(@PathVariable UUID id)
    {
        Optional<Drink> drink = drinkService.getDrinkById(id);
        if(drink.isPresent())
        {
            return ResponseEntity.ok(mapToDrinkReadDTO(drink.get()));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public DrinksReadDTO getAllDrinks()
    {
        List<Drink> drinks = drinkService.getAllDrinks();

        if(drinks.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else {
            return drinksToResponseFunction.apply(drinkService.getAllDrinks());
        }
    }

    @GetMapping("/{id}/drinks")
    public DrinksReadDTO getAllBrandsDrinks(@PathVariable UUID id)
    {
           return drinkService.getAllByBrand(id)
                   .map(drinksToResponseFunction)
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DrinkReadDTO> createDrink(@RequestBody DrinkCreateUpdateDTO drinkDTO)
    {
        Optional<Brand> brand = brandService.getBrandById(drinkDTO.getBrandId());
        if(brand.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }


        Drink newDrink = mapToDrink(drinkDTO);
        newDrink.setBrand(brand.get());
        drinkService.createDrink(newDrink);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDrinkReadDTO(newDrink));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrinkReadDTO> updateDrink(@PathVariable UUID id, @RequestBody DrinkCreateUpdateDTO drinkDTO)
    {
        Optional<Drink> existingDrink = drinkService.getDrinkById(id);
        if(existingDrink.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        Drink updatedDrink = mapToDrink(drinkDTO);
        updatedDrink.setId(existingDrink.get().getId());
        updatedDrink.setBrand(existingDrink.get().getBrand());
        drinkService.updateDrink(id,updatedDrink);
        return ResponseEntity.ok(mapToDrinkReadDTO(updatedDrink));
    }

    @PatchMapping
    public void patchDrink(@PathVariable UUID id, @RequestBody DrinkCreateUpdateDTO drinkDTO){
        drinkService.getDrinkById(id)
                .ifPresentOrElse(
                        drink -> drinkService.updateDrink(updateDrinkWithRequestFunction.apply(drink,drinkDTO)),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable UUID id)
    {
        drinkService.deleteDrink(id);
        return ResponseEntity.noContent().build();
    }

    private DrinkReadDTO mapToDrinkReadDTO(Drink drink)
    {
        return DrinkReadDTO.builder()
                .id(drink.getId())
                .name(drink.getName())
                .brand(mapToBrandReadDTO(drink.getBrand()))
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

    private BrandReadDTO mapToBrandReadDTO(Brand brand){
        return BrandReadDTO.builder()
                .id(brand.getId())
                .build();
    }


}
