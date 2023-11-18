package com.example.lab4Brand;

import com.example.lab4Brand.Class.Brand;
import com.example.lab4Brand.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements InitializingBean{

    private final BrandService brandService;

    @Autowired
    public DataInitializer(BrandService brandService)
    {
        this.brandService = brandService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Brand Pepsi = Brand.builder()
                .name("Pepsi")
                .country("USA")
                .build();
        Brand Fanta = Brand.builder()
                .name("Fanta")
                .country("Germany")
                .build();
        Brand CocaCola = Brand.builder()
                .name("Coca-Cola")
                .country("USA")
                .build();

        brandService.createBrand(Pepsi);
        brandService.createBrand(Fanta);
        brandService.createBrand(CocaCola);

    }
}
