package dto;

public class TraducaoDTO {
    private String original;
    private String traduzido;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTraduzido() {
        return traduzido;
    }

    public void setTraduzido(String traduzido) {
        this.traduzido = traduzido;
    }

    @Override
    public String toString() {
        return "Original: " + original + "\nTraduzido: " + traduzido;
    }
}
