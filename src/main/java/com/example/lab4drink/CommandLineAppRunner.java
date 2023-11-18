package com.example.lab4drink;

import com.example.lab4drink.Class.Brand;
import com.example.lab4drink.Class.Drink;
import com.example.lab4drink.Service.BrandService;
import com.example.lab4drink.Service.DrinkService;
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
            System.out.println("add_drink - Add a new drink");
            System.out.println("delete_drink - Delete an existing drink");
            System.out.println("exit - Stop the application");

            System.out.print("Enter a command: ");
            String command = scanner.next();

            switch (command) {

                case "list_drinks":
                    System.out.println(drinkService.getAllDrinks());
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

                case "delete_drink":
                    System.out.print("Enter drink name: ");
                    String deleteDrinkName = scanner.next();
                    drinkService.deleteDrink(deleteDrinkName);
                    System.out.println("Drink deleted.");
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
        System.exit(0);
    }

}
