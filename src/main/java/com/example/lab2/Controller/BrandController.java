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
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<BrandReadDTO> getBrandById(@PathVariable String name) {
        Brand brand = brandService.getBrandByName(name);
        if (brand != null) {
            return ResponseEntity.ok(mapToBrandReadDTO(brand));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BrandReadDTO>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        List<BrandReadDTO> brandReadDTOs = brands.stream()
                .map(this::mapToBrandReadDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(brandReadDTOs);
    }

    @PostMapping
    public ResponseEntity<BrandReadDTO> createBrand(@RequestBody BrandCreateUpdateDTO brandDTO) {
        Brand newBrand = mapToBrand(brandDTO);
        brandService.createBrand(newBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToBrandReadDTO(newBrand));
    }

    @PutMapping("/{name}")
    public ResponseEntity<BrandReadDTO> updateBrand(@PathVariable String name, @RequestBody BrandCreateUpdateDTO brandDTO) {

        Brand existingBrand = brandService.getBrandByName(name);
        if (existingBrand == null) {
            return ResponseEntity.notFound().build();
        }

        Brand updatedBrand = mapToBrand(brandDTO);
        updatedBrand.setId(existingBrand.getId());
        brandService.updateBrand(name, updatedBrand);
        return ResponseEntity.ok(mapToBrandReadDTO(updatedBrand));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String name) {
        brandService.deleteBrand(name);
        return ResponseEntity.noContent().build();
    }

    private BrandReadDTO mapToBrandReadDTO(Brand brand)
    {
        return BrandReadDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .country(brand.getCountry())
                .build();
    }

    private Brand mapToBrand(BrandCreateUpdateDTO brandDTO)
    {
        return Brand.builder()
                .name(brandDTO.getName())
                .country(brandDTO.getCountry())
                .build();
    }
}
