package Model;

import java.util.List;
public class Rei extends Peca {

    public Rei(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false, "R");
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return verificaMovimentoNormal(partida, destino) || verificaRoque(partida, destino);
    }

    private boolean verificaMovimentoNormal(Casa partida, Casa destino) {
        return (Math.abs(partida.linha - destino.linha) == 1
                || Math.abs(partida.linha - destino.linha) == 0)
                && (Math.abs(partida.coluna - destino.coluna) == 1
                        || Math.abs(partida.coluna - destino.coluna) == 0);
    }

    public boolean verificaRoque(Casa partida, Casa destino) {
        Tabuleiro tabuleiro = this.getTabuleiro();
        Peca torre;

        String partidaEDestino = Casa.casaToStr(partida) + Casa.casaToStr(destino);

        //System.out.println(partidaEDestino);

        switch (partidaEDestino) {
            // Roque curto das brancas
            case "e1g1" -> {
                torre = tabuleiro.getPeca(Casa.strToCasa("h1"));
                return verificaCondicaoRoque(partida, destino, torre);
            }

            // Roque grande das brancas
            case "e1c1" -> {
                torre = tabuleiro.getPeca(Casa.strToCasa("a1"));
                return verificaCondicaoRoque(partida, destino, torre);
            }


            // Roque curto das pretas
            case "e8g8" -> {
                torre = tabuleiro.getPeca(Casa.strToCasa("h8"));
                return verificaCondicaoRoque(partida, destino, torre);
            }


            // Roque grande das pretas
            case "e8c8" -> {
                torre = tabuleiro.getPeca(Casa.strToCasa("a8"));
                return verificaCondicaoRoque(partida, destino, torre);
            }


            // Não é roque
            default -> {
                return false;
            }
        }

    }

    private boolean verificaCondicaoRoque(Casa partida, Casa destino, Peca torre) {
        Tabuleiro tabuleiro = this.getTabuleiro();

        Peca rei = tabuleiro.getPeca(partida);

        if (rei.numMovimentos > 0 || torre.numMovimentos > 0 || !(torre instanceof Torre))
            return false;

        int minColuna = Math.min(partida.coluna, destino.coluna);
        int maxColuna = Math.max(partida.coluna, destino.coluna);

        for (int i = minColuna; i <= maxColuna; i++) {
            Casa casaAChecar = new Casa(partida.linha, i);
            if (tabuleiro.estaAmeacado(casaAChecar, rei.getJogador()))
                return false;
        }
        return true;
    }
    
    

    protected static List<Casa> roque(Casa partida, Casa destino) {
        
        String idRoque = Casa.casaToStr(partida) + Casa.casaToStr(destino);
        
        List<Casa> casasRoque;

        Casa casaAntigaTorre = null;
        Casa casaNovaTorre = null;

        switch (idRoque) {
            case "e1g1" -> {
                casaAntigaTorre = Casa.strToCasa("h1");
                casaNovaTorre = Casa.strToCasa("f1");
            }
            case "e1c1" -> {
                casaAntigaTorre = Casa.strToCasa("a1");
                casaNovaTorre = Casa.strToCasa("d1");
            }
            case "e8g8" -> {
                casaAntigaTorre = Casa.strToCasa("h8");
                casaNovaTorre = Casa.strToCasa("f8");
            }
            case "e8c8" -> {
                casaAntigaTorre = Casa.strToCasa("a8");
                casaNovaTorre = Casa.strToCasa("d8");
            }
        }
        casasRoque = List.of(new Casa[]{partida, casaAntigaTorre, destino, casaNovaTorre});
        
        return casasRoque;


    }

    public String simboloPeca() {
        return "R";
    }
}
