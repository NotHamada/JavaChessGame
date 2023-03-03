public class Bispo extends Peca {

    public Bispo(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    @Override
    public String toString() {
        return "B";
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        if (!dentroDoTabuleiro(destino))
            return false;

        int distColuna = Math.abs(partida.coluna - destino.coluna);

        return destino.linha + distColuna == partida.linha || destino.linha - distColuna == partida.linha;
    }
}
