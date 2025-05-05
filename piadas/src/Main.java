import java.io.IOException;

import dto.TraducaoDTO;
import service.ApiExterna;
import utils.Tradutor;



public class Main {
public static void main(String[] args) {
        String texto = "I love programming!";
        String idiomaOrigem = "en";
        String idiomaDestino = "pt";

        try {
            TraducaoDTO resultado = Tradutor.traduzirTexto(texto, idiomaOrigem, idiomaDestino);
            System.out.println(resultado);
        } catch (IOException e) {
            System.out.println("Erro ao traduzir: " + e.getMessage());
        }

        ApiExterna apiExterna = new ApiExterna();
        apiExterna.getApi();
    }
}
