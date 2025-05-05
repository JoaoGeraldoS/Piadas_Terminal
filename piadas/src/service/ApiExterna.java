package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import dto.PiadasDTO;

public class ApiExterna {

    public PiadasDTO getApi() {
        String apiUrl = "https://official-joke-api.appspot.com/jokes/random";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());

            String responseBody = response.body();
            PiadasDTO piadasDTO = parseJsonManualmente(responseBody);

            // System.out.println("Joke:");
            // System.out.println("ID: " + joke.getId());
            // System.out.println("Tipo: " + joke.getType());
            // System.out.println("Pergunta: " + piadasDTO.getSetup());
            // System.out.println("Resposta: " + piadasDTO.getPunchline());

            return piadasDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    
    private PiadasDTO parseJsonManualmente(String json) {
        PiadasDTO piada = new PiadasDTO();

        piada.setId(getIntValue(json, "id"));
        piada.setType(getStringValue(json, "type"));
        piada.setSetup(getStringValue(json, "setup"));
        piada.setPunchline(getStringValue(json, "punchline"));

        return piada;
    }

    private String getStringValue(String json, String key) {
        int keyIndex = json.indexOf("\"" + key + "\"");
        int colonIndex = json.indexOf(":", keyIndex);
        int firstQuote = json.indexOf("\"", colonIndex + 1);
        int secondQuote = json.indexOf("\"", firstQuote + 1);
        return json.substring(firstQuote + 1, secondQuote);
    }

    private int getIntValue(String json, String key) {
        int keyIndex = json.indexOf("\"" + key + "\"");
        int colonIndex = json.indexOf(":", keyIndex);
        int commaIndex = json.indexOf(",", colonIndex);
        if (commaIndex == -1) {
            commaIndex = json.indexOf("}", colonIndex);
        }
        String number = json.substring(colonIndex + 1, commaIndex).trim();
        return Integer.parseInt(number);
    }
}
