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

    @GetMapping("/{id}")
    public ResponseEntity<BrandReadDTO> getBrandById(@PathVariable UUID id) {
        Brand brand = brandService.getBrandById(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<BrandReadDTO> updateBrand(@PathVariable UUID id, @RequestBody BrandCreateUpdateDTO brandDTO) {
        Brand existingBrand = brandService.getBrandById(id);
        if (existingBrand == null) {
            return ResponseEntity.notFound().build();
        }

        Brand updatedBrand = mapToBrand(brandDTO);
        updatedBrand.setId(id);
        brandService.updateBrand(id, updatedBrand);
        return ResponseEntity.ok(mapToBrandReadDTO(updatedBrand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrand(id);
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
