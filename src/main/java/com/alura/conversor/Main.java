package com.alura.conversor;

import com.alura.conversor.api.ExchangeRateApiController;
import com.alura.conversor.models.ConversionResume;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int userInput = 0;
        String inputAmount;
        String inputBaseCode;
        String inputTargetCode;

        while (true) {
            System.out.println("------------------------");
            System.out.println("Conversor de Moneda");
            System.out.println("------------------------");

            if (userInput == 1) {
                break;
            }

            System.out.print("Monto: ");
            inputAmount = sc.next();

            System.out.print("Convertir de: ");
            inputBaseCode = sc.next();

            System.out.print("A: ");
            inputTargetCode = sc.next();

            ExchangeRateApiController res1 = new ExchangeRateApiController(inputBaseCode, inputTargetCode, inputAmount);
            System.out.println(res1);

            System.out.println("Salir: ");
            userInput = sc.nextInt();

        }

    }
}
