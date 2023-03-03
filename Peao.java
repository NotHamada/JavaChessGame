public class Peao extends Peca {

    public Peao(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        if (!dentroDoTabuleiro(destino))
            return false;

        int sinal = (this.getJogador() == Cor.Brancas) ? 1 : -1;

        boolean movimentoDeCaptura = (partida.coluna + 1 == destino.coluna || partida.coluna - 1 == destino.coluna)
                && partida.linha + sinal == destino.linha
                && this.getTabuleiro().getPeca(partida.linha + sinal, destino.coluna) != null;

        boolean movimentoNormal = partida.coluna == destino.coluna
                && (partida.linha + sinal == destino.linha
                || partida.linha + (2 * sinal) == destino.linha);

        return movimentoDeCaptura || movimentoNormal;
    }

    @Override
    public String toString() {
        return "P";
    }

}
