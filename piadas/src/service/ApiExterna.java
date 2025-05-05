package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiExterna {

    public void getApi() {
        String apiUrl = "https://official-joke-api.appspot.com/jokes/random";

            // Criação do HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Criando a requisição GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()  // Método GET
                    .build();

            try {
                // Enviando a requisição e obtendo a resposta
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Imprimindo o status code e o corpo da resposta
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Response Body: " + response.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
