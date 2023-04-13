package Model;

public class Bispo extends Peca {

    public Bispo(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, "B");
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        int distColuna = Math.abs(partida.coluna - destino.coluna);

        return destino.linha + distColuna == partida.linha || destino.linha - distColuna == partida.linha;
    }
}
