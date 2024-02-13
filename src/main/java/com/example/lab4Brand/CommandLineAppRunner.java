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
            System.out.println("exit - Stop the application");

            System.out.print("Enter a command: ");
            String command = scanner.next();

            switch (command) {
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
