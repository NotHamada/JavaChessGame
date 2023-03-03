public class Peao extends Peca{

    public Peao(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        int sinal = 1;
        if (this.getJogador() == Cor.Pretas)
            sinal = -1;

        return (partida.coluna == destino.coluna
                && (partida.linha + (1 * sinal) == destino.linha
                        || partida.linha + (2 * sinal) == destino.linha));
    }

    @Override
    public String toString() {
        return "P";
    }
    
    
}
