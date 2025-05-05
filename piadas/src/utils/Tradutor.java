package utils;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import dto.TraducaoDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class Tradutor {
    private static final String subscriptionKey = "9khpRkmxcM9JxQt8QvLHn8eL9ZNhPrrv0Hqtzv2dRzjhkdAFIq04JQQJ99BEACZoyfiXJ3w3AAAbACOGzujV";
    private static final String endpoint = "https://api.cognitive.microsofttranslator.com/";
    private static final String region = "brazilsouth"; // exemplo: "eastus"

    public static TraducaoDTO traduzirTexto(String texto, String de, String para) throws IOException {
        String urlStr = endpoint + "/translate?api-version=3.0&from=" + de + "&to=" + para;
        URL url = new URL(urlStr);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setRequestProperty("Ocp-Apim-Subscription-Region", region);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonBody = "[{\"Text\":\"" + texto + "\"}]";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        StringBuilder resposta = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                resposta.append(linha.trim());
            }
        }

        connection.disconnect();

        // Processamento simples do JSON sem biblioteca externa
        String respostaStr = resposta.toString();
        String traduzido = extrairTextoTraduzido(respostaStr);

        TraducaoDTO dto = new TraducaoDTO();
        dto.setOriginal(texto);
        dto.setTraduzido(traduzido);
        return dto;
    }

    private static String extrairTextoTraduzido(String json) {
        // Exemplo de resposta:
        // [{"translations":[{"text":"Eu amo programar!","to":"pt"}]}]
        int start = json.indexOf("\"text\":\"") + 8;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
