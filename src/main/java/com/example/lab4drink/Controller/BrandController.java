package com.example.lab4drink.Controller;

import com.example.lab4drink.Class.*;
import com.example.lab4drink.Service.*;
import  org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable UUID id) {

        Brand existingBrand = brandService.getBrandById(id);
        if(existingBrand == null)
        {
            return ResponseEntity.notFound().build();
        }

        List<Drink> drinks = existingBrand.getDrinks();
        if(drinks!=null && !drinks.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cannot delete brand with associated drinks.");
        }
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

}
