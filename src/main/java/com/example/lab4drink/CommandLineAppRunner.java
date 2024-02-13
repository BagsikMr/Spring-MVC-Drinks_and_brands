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
        UUID brandId;

        while (isRunning) {
            System.out.println("list_drinks - wiesz co");
            System.out.println("exit - Stop the application");

            System.out.print("Enter a command: ");
            String command = scanner.next();

            switch (command) {

                case "list_drinks":
                    System.out.println(drinkService.getAllDrinks());
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
