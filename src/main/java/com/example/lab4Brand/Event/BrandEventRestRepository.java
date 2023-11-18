package com.example.lab4Brand.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class BrandEventRestRepository implements BrandEventRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public BrandEventRestRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void deleteBrand(UUID id) {
        restTemplate.delete("/{id}",id);
    }

}
