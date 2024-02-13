package com.example.lab4Brand.Controller;

import com.example.lab4Brand.Class.*;
import com.example.lab4Brand.Function.BrandToResponseFunction;
import com.example.lab4Brand.Function.PutRequestToBrandFunction;
import com.example.lab4Brand.Function.UpdateBrandWithRequestFunction;
import com.example.lab4Brand.Service.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.lab4Brand.DTO.*;
import com.example.lab4Brand.Function.BrandsToResponseFunction;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;
    private final BrandsToResponseFunction brandsToResponse;
    private final BrandToResponseFunction brandToResponseFunction;
    private final PutRequestToBrandFunction putRequestToBrandFunction;
    private final UpdateBrandWithRequestFunction updateBrandWithRequestFunction;

    @Autowired
    public BrandController(
            BrandService brandService,
            BrandsToResponseFunction brandsToResponse,
            BrandToResponseFunction brandToResponseFunction,
            PutRequestToBrandFunction putRequestToBrandFunction,
            UpdateBrandWithRequestFunction updateBrandWithRequestFunction) {
        this.brandService = brandService;
        this.brandsToResponse = brandsToResponse;
        this.brandToResponseFunction = brandToResponseFunction;
        this.putRequestToBrandFunction = putRequestToBrandFunction;
        this.updateBrandWithRequestFunction = updateBrandWithRequestFunction;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandReadDTO> getBrandById(@PathVariable UUID id) {
        Optional<Brand> brand = brandService.getBrandById(id);
        return brand.map(b -> ResponseEntity.ok(mapToBrandReadDTO(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public BrandsReadDTO getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        if(brands.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        else {
            return brandsToResponse.apply(brandService.getAllBrands());
        }
    }

    @PostMapping
    public ResponseEntity<BrandReadDTO> createBrand(@RequestBody BrandCreateUpdateDTO brandDTO) {
        Brand newBrand = mapToBrand(brandDTO);
        brandService.createBrand(newBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToBrandReadDTO(newBrand));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BrandReadDTO> updateBrand(@PathVariable UUID id, @RequestBody BrandCreateUpdateDTO brandDTO) {

        Optional<Brand> existingBrand = brandService.getBrandById(id);
        if (existingBrand.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Brand updatedBrand = mapToBrand(brandDTO);
        updatedBrand.setId(existingBrand.get().getId());
        brandService.updateBrand(id, updatedBrand);
        return ResponseEntity.ok(mapToBrandReadDTO(updatedBrand));
    }

    @PutMapping
    public void putBrand(@RequestBody BrandCreateUpdateDTO request){
        brandService.createBrand(putRequestToBrandFunction.apply(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable UUID id) {

        Optional<Brand> existingBrand = brandService.getBrandById(id);
        if(existingBrand.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

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
