package com.example.lab4drink;

import com.example.lab4drink.Class.Brand;
import com.example.lab4drink.Class.Drink;
import com.example.lab4drink.Service.BrandService;
import com.example.lab4drink.Service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements InitializingBean{
    private final DrinkService drinkService;
    private final BrandService brandService;

    @Autowired
    public DataInitializer(DrinkService drinkService, BrandService brandService)
    {
        this.drinkService = drinkService;
        this.brandService = brandService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Brand Pepsi = Brand.builder()
                .id(UUID.fromString("af51946f-ee8d-414a-b7d7-701df96adb75"))
                .build();
        Brand Fanta = Brand.builder()
                .id(UUID.fromString("28d2d6e6-b051-4e33-9539-42710b10a822"))
                .build();
        Brand CocaCola = Brand.builder()
                .id(UUID.fromString("9bbc8579-b5aa-4258-91de-668aa01de157"))
                .build();

        brandService.createBrand(Pepsi);
        brandService.createBrand(Fanta);
        brandService.createBrand(CocaCola);

        Drink cocacola_classic = Drink.builder()
                .name("Coca-Cola_classic")
                .brand(CocaCola)
                .price(5)
                .year("1886")
                .build();

        Drink pepsi_classic = Drink.builder()
                .name("Pepsi_classic")
                .brand(Pepsi)
                .price(4)
                .year("1893")
                .build();

        Drink fanta_orange = Drink.builder()
                .name("Fanta_orange")
                .brand(Fanta)
                .price(2)
                .year("1940")
                .build();

        drinkService.createDrink(cocacola_classic);
        drinkService.createDrink(pepsi_classic);
        drinkService.createDrink(fanta_orange);

    }
}
