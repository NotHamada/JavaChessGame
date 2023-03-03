public class Dama extends Peca {

    public Dama(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return true;
    }

    @Override
    public String toString() {
        return "D";
    }
}
