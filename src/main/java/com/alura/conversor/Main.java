package com.alura.conversor;

import com.alura.conversor.api.ExchangeRateApiController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExchangeRateApiController exchangeRate = new ExchangeRateApiController();
        Scanner sc = new Scanner(System.in);
        int userInput;

        do {
            menu(exchangeRate);
            userInput = sc.nextInt();

            if (userInput >= 1 && userInput <= exchangeRate.getConversionRates().size()) {
                System.out.print("Ingrese el valor que desea convertir: ");
                double ammount = sc.nextDouble();

                makeConversion(userInput - 1, ammount, exchangeRate);
            } else if (userInput == exchangeRate.getConversionRates().size() + 1) {
                System.out.println("Saliendo del programa");
            } else {
                System.out.println("Opción no valida");
            }
        } while (userInput != exchangeRate.getConversionRates().size() + 1);
        sc.close();
    }

    private static void menu(ExchangeRateApiController exchangeRate) {
        System.out.println("***************************");

        for (int i = 0; i < exchangeRate.getConversionRates().size(); i++) {
            System.out.println((i + 1) + ". " + exchangeRate.getConversionRates().get(i));
        }

        System.out.println((exchangeRate.getConversionRates().size() + 1) + ". Salir");
        System.out.println("***************************");
        System.out.print("Elige una opción: ");
    }

    private static void makeConversion(int index, double ammount, ExchangeRateApiController exchangeRate) {
        double res = exchangeRate.makeConversion(ammount, index);
        System.out.printf("El resultado de la conversión es: %.2f\n", res);

    }
}
