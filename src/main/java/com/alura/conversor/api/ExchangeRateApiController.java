package com.alura.conversor.api;

import com.alura.conversor.config.ConfigHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ExchangeRateApiController {
    private static final String apiKey = ConfigHandler.getApiKey();
    private static final String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

    private ArrayList<String> conversionRates = new ArrayList<>();
    private ArrayList<Double> exchangeRates = new ArrayList<>();

    public ExchangeRateApiController() {
        try {
            apiRequest();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la conversion: " + e.getMessage());
        }
    }

    private void apiRequest() throws Exception {
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

            conversionRates.add("USD_TO_COP");
            exchangeRates.add(rates.get("COP").getAsDouble());

            conversionRates.add("USD_TO_BRL");
            exchangeRates.add(rates.get("BRL").getAsDouble());

            conversionRates.add("USD_TO_ARS");
            exchangeRates.add(rates.get("ARS").getAsDouble());

            conversionRates.add("COP_TO_USD");
            exchangeRates.add(1 / rates.get("COP").getAsDouble());

            conversionRates.add("BRL_TO_USD");
            exchangeRates.add(1 / rates.get("BRL").getAsDouble());

            conversionRates.add(("ARS_TO_USD"));
            exchangeRates.add(1 / rates.get("ARS").getAsDouble());
        } else {
            throw new Exception("Error al conectar con la API, codigo de respuesta: " + responseCode);
        }
    }

    public double makeConversion (double ammount, int conversionRate) {
        if (conversionRate >= 0 && conversionRate < conversionRates.size()) {
            return ammount * exchangeRates.get(conversionRate);
        } else {
            throw new IllegalArgumentException("Tasa de conversion no valida");
        }
    }

    public ArrayList<String> getConversionRates() {
        return conversionRates;
    }
}