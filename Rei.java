public class Rei extends Peca {

    public Rei(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        if (!dentroDoTabuleiro(destino))
            return false;

        return (Math.abs(partida.linha - destino.linha) == 1
                || Math.abs(partida.linha - destino.linha) == 0)
                    && (Math.abs(partida.coluna - destino.coluna) == 1
                    || Math.abs(partida.coluna - destino.coluna) == 0);
    }

    @Override
    public String toString() {
        return "R";
    }
}
