package com.example.lab4Brand.Event;

import com.example.lab4Brand.Class.Brand;

import java.util.UUID;

public interface BrandEventRepository {
    void deleteBrand(UUID id);
    void create(Brand brand);
}
