package com.example.lab4Brand;

import com.example.lab4Brand.Class.Brand;
import com.example.lab4Brand.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLineAppRunner implements CommandLineRunner{

    private final BrandService brandService;

    @Autowired
    public CommandLineAppRunner(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        String brandName;

        while (isRunning) {
            System.out.println("list_brands - List all brands");
            System.out.println("add_brand - Add a new brand");
            System.out.println("delete_brand - Delete an existing brand");
            System.out.println("exit - Stop the application");

            System.out.print("Enter a command: ");
            String command = scanner.next();

            switch (command) {

                case "list_brands":
                    for(Brand brand : brandService.getAllBrands())
                    {
                        System.out.println(brand);
                    }
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
        System.exit(0);
    }

}
