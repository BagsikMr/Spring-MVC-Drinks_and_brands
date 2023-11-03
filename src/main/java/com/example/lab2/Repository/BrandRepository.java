package com.example.lab2.Repository;

import com.example.lab2.Class.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> { ;
    List<Brand> findByCountry(String country);
    Brand findByName(String name);
    void deleteBrandByName(String name);
}
