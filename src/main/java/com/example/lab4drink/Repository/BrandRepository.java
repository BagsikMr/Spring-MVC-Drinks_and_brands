package com.example.lab4drink.Repository;

import com.example.lab4drink.Class.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> { ;
    List<Brand> findByCountry(String country);
    Brand findByName(String name);
    void deleteBrandByName(String name);
}
