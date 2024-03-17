package Model;

import java.util.List;

public class Rei extends Peca {

    public Rei(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, "R");
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return verificaMovimentoNormal(partida, destino) || verificaRoque(partida, destino);
    }

    public boolean verificaRoque(Casa partida, Casa destino) {
        Tabuleiro tabuleiro = this.getTabuleiro();
        Peca torre = null;

        String partidaEDestino = Casa.casaToStr(partida) + Casa.casaToStr(destino);

        if (isRoqueCurtoDasBrancas(partidaEDestino))
            torre = tabuleiro.getPeca(Casa.strToCasa("h1"));
        else if (isRoqueGrandeDasBrancas(partidaEDestino))
            torre = tabuleiro.getPeca(Casa.strToCasa("a1"));
        else if (isRoqueCurtoDasPretas(partidaEDestino))
            torre = tabuleiro.getPeca(Casa.strToCasa("h8"));
        else if (isRoqueGrandeDasPretas(partidaEDestino))
            torre = tabuleiro.getPeca(Casa.strToCasa("a8"));

        if (torre != null)
            return verificaCondicaoRoque(partida, destino, torre);
        
        return false;
    }

    protected List<Casa> roque(Casa partida, Casa destino) {
        String idRoque = Casa.casaToStr(partida) + Casa.casaToStr(destino);

        Casa casaAntigaTorre = null;
        Casa casaNovaTorre = null;

        if (isRoqueCurtoDasBrancas(idRoque)) {
            casaAntigaTorre = Casa.strToCasa("h1");
            casaNovaTorre = Casa.strToCasa("f1");
        }
        else if (isRoqueGrandeDasBrancas(idRoque)) {
            casaAntigaTorre = Casa.strToCasa("a1");
            casaNovaTorre = Casa.strToCasa("d1");
        }
        else if (isRoqueCurtoDasPretas(idRoque)) {
            casaAntigaTorre = Casa.strToCasa("h8");
            casaNovaTorre = Casa.strToCasa("f8");
        }
        else if (isRoqueGrandeDasPretas(idRoque)) {
            casaAntigaTorre = Casa.strToCasa("a8");
            casaNovaTorre = Casa.strToCasa("d8");
        }
        return List.of(new Casa[]{partida, casaAntigaTorre, destino, casaNovaTorre});
    }

    private boolean verificaMovimentoNormal(Casa partida, Casa destino) {
        return (Math.abs(partida.linha - destino.linha) == 1
                || Math.abs(partida.linha - destino.linha) == 0)
                && (Math.abs(partida.coluna - destino.coluna) == 1
                        || Math.abs(partida.coluna - destino.coluna) == 0);
    }

    private boolean isRoqueCurtoDasBrancas(String str) { return str.equals("e1g1"); }
    private boolean isRoqueGrandeDasBrancas(String str) { return str.equals("e1c1"); }
    private boolean isRoqueCurtoDasPretas(String str) { return str.equals("e8g8"); }
    private boolean isRoqueGrandeDasPretas(String str) { return str.equals("e8c8"); }

    private boolean verificaCondicaoRoque(Casa partida, Casa destino, Peca torre) {
        Tabuleiro tabuleiro = this.getTabuleiro();

        if (this.numMovimentos > 0 || torre.numMovimentos > 0 || !(torre instanceof Torre))
            return false;

        int minColuna = Math.min(partida.coluna, destino.coluna);
        int maxColuna = Math.max(partida.coluna, destino.coluna);
        for (int i = minColuna; i <= maxColuna; i++) {
            Casa casaAChecar = new Casa(partida.linha, i);
            if (tabuleiro.estaAmeacado(casaAChecar, this.getJogador()))
                return false;
        }
        return true;
    }

    public String simboloPeca() {
        return "R";
    }
}
