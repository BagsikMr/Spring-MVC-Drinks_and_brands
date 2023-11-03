package com.example.lab2;

import com.example.lab2.Class.Brand;
import com.example.lab2.Class.Drink;
import com.example.lab2.Service.BrandService;
import com.example.lab2.Service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

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

    /*
    @PostConstruct
    public void initData(){
    */
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
