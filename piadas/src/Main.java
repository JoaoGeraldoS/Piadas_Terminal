import java.io.IOException;

import dto.PiadasDTO;
import dto.TraducaoDTO;
import service.ApiExterna;
import utils.Tradutor;

public class Main {
public static void main(String[] args) {
        ApiExterna apiExterna = new ApiExterna();

        PiadasDTO piadas = apiExterna.getApi();
        String texto = piadas.getSetup();
        String texto2 = piadas.getPunchline();
        String idiomaOrigem = "en";
        String idiomaDestino = "pt";

        try {
            TraducaoDTO resultado = Tradutor.traduzirTexto(texto, idiomaOrigem, idiomaDestino);
            TraducaoDTO resultado2 = Tradutor.traduzirTexto(texto2, idiomaOrigem, idiomaDestino);
            System.out.println(resultado.getOriginal());
            System.out.println(resultado2.getOriginal());


            System.out.println(resultado.getTraduzido());
            System.out.println(resultado2.getTraduzido());
        } catch (IOException e) {
            System.out.println("Erro ao traduzir: " + e.getMessage());
        }

        
        apiExterna.getApi();
    }
}
