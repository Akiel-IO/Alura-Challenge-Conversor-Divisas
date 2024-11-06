package com.alura.conversor.api;

import com.alura.conversor.config.ConfigHandler;
import com.alura.conversor.models.ApiRecord;
import com.alura.conversor.models.ConversionResume;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApiController {
    String baseCode;
    String targetCode;
    String amount;

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public ExchangeRateApiController(String baseCode, String targetCode, String amount) throws IOException, InterruptedException {

        String apiKey = ConfigHandler.getApiKey();
        String baseURL = "https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+baseCode+"/"+targetCode;

        try {
            System.out.println(baseURL);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseURL)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            //System.out.println(json);

            ApiRecord apiRecord = gson.fromJson(json, ApiRecord.class);

            ConversionResume resume = new ConversionResume(apiRecord, amount);
            System.out.println(resume.showResume());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
