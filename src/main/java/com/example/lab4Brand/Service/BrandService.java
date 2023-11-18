package com.example.lab4Brand.Service;

import com.example.lab4Brand.Class.*;
import com.example.lab4Brand.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(UUID id) {
        return brandRepository.findById(id);
    }
    public Brand getBrandByName(String name)
    {
        return brandRepository.findByName(name);
    }

    public void createBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public void updateBrand(UUID id, Brand updatedBrand) {
        if (brandRepository.existsById(id)) {
            updatedBrand.setId(id);
            brandRepository.save(updatedBrand);
        }
    }
    public void updateBrand(String name, Brand updatedBrand) {
        UUID id = brandRepository.findByName(name).getId();
        Brand existingBrand = brandRepository.findByName(name);
        if(existingBrand != null)
        {
            updatedBrand.setId(existingBrand.getId());
            existingBrand.setName(updatedBrand.getName());
            existingBrand.setCountry(updatedBrand.getCountry());

            brandRepository.save(existingBrand);
        }

    }

    public void deleteBrand(UUID id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand != null) {
            brandRepository.deleteById(id);
        }
    }
    public void deleteBrand(String name) {
        Brand brand = brandRepository.findByName(name);
        if (brand != null) {
            brandRepository.deleteById(brand.getId());
        }
    }


}

