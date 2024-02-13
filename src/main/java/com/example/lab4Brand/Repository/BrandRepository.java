package com.example.lab4Brand.Repository;

import com.example.lab4Brand.Class.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> { ;
    List<Brand> findByCountry(String country);
    Optional<Brand> findByName(String name);
    void deleteBrandByName(String name);
}
