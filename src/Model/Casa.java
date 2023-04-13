package Model;

public class Casa {
    public int linha;
    public int coluna;

    public Casa(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public static Casa strToCasa(String posicao) {
        int linha = '8' - posicao.charAt(1);
        int coluna = posicao.charAt(0) - 'a';

        return new Casa(linha, coluna);
    }
    public static String casaToStr(Casa casa) {

        return (char) (casa.coluna + 'a') + String.valueOf(8 - casa.linha);
    }


    public boolean equals(Casa c) {
        return linha == c.linha && coluna == c.coluna;
    }
}
