package com.example.lab4Brand.Service;

import com.example.lab4Brand.Class.*;
import com.example.lab4Brand.Event.BrandEventRepository;
import com.example.lab4Brand.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandEventRepository brandEventRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository,BrandEventRepository brandEventRepository) {
        this.brandRepository = brandRepository;
        this.brandEventRepository = brandEventRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(UUID id) {
        return brandRepository.findById(id);
    }

    public void createBrand(Brand brand) {
        brandEventRepository.create(brand);
        brandRepository.save(brand);
    }

    public void updateBrand(UUID id, Brand updatedBrand) {
        if (brandRepository.existsById(id)) {
            updatedBrand.setId(id);
            brandRepository.save(updatedBrand);
        }
    }

    public void deleteBrand(UUID id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand != null) {
            brandRepository.deleteById(id);
            brandEventRepository.deleteBrand(id);
        }
    }

    public void save(Brand brand){brandRepository.save(brand);}


}

