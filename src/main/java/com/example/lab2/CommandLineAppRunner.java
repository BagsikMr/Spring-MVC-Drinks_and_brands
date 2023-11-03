package com.example.lab2;

import com.example.lab2.Class.Brand;
import com.example.lab2.Class.Drink;
import com.example.lab2.Service.BrandService;
import com.example.lab2.Service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineAppRunner implements CommandLineRunner{

    private final DrinkService drinkService;
    private final BrandService brandService;

    @Autowired
    public CommandLineAppRunner(DrinkService drinkService, BrandService brandService) {
        this.drinkService = drinkService;
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        String brandName;

        while (isRunning) {
            System.out.println("list_drinks - List all drinks");
            System.out.println("list_brands - List all brands");
            System.out.println("add_drink - Add a new drink");
            System.out.println("add_brand - Add a new brand");
            System.out.println("delete_drink - Delete an existing drink");
            System.out.println("exit_brand - Delete an existing brand");
            System.out.println("exit - Stop the application");

            System.out.print("Enter a command: ");
            String command = scanner.next();

            switch (command) {

                case "list_drinks":
                    System.out.println(drinkService.getAllDrinks());
                    break;

                case "list_brands":
                    System.out.println(brandService.getAllBrands());
                    break;

                case "add_drink":
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter brand name: ");
                    brandName = scanner.next();
                    Brand brand = brandService.getBrandByName(brandName);
                    if(brand == null)
                    {
                        System.out.println("Brand with this name does not exist.");
                        break;
                    }
                    System.out.print("Enter price: ");
                    int price = scanner.nextInt();
                    System.out.print("Enter year: ");
                    String year = scanner.next();

                    Drink newDrink = Drink.builder()
                            .id(UUID.randomUUID())
                            .name(name)
                            .brand(brand)
                            .price(price)
                            .year(year)
                            .build();
                    drinkService.createDrink(newDrink);
                    System.out.println("Drink added.");
                    break;

                case "add_brand":
                    System.out.print("Enter name: ");
                    brandName = scanner.next();
                    System.out.print("Enter country: ");
                    String country = scanner.next();
                    Brand newBrand = Brand.builder()
                            .name(brandName)
                            .country(country)
                            .build();
                    brandService.createBrand(newBrand);
                    System.out.println("Brand added.");
                    break;

                case "delete_drink":
                    System.out.print("Enter drink name: ");
                    String deleteDrinkName = scanner.next();
                    drinkService.deleteDrink(deleteDrinkName);
                    System.out.println("Drink deleted.");
                    break;

                case "delete_brand":
                    System.out.println("Enter brand name: ");
                    String deleteBrandName = scanner.next();
                    brandService.deleteBrand(deleteBrandName);
                    System.out.println("Brand deleted.");
                    break;

                case "exit":
                    isRunning = false;
                    break;

                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }

        System.out.println("Exiting the application.");
    }

}
