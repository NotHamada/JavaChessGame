public class Torre extends Peca {

    public Torre(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        if (!dentroDoTabuleiro(destino))
            return false;

        return partida.coluna == destino.coluna || partida.linha == destino.linha;
    }

    @Override
    public String toString() {
        return "T";
    }
}
