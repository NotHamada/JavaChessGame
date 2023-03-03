public class Torre extends Peca {

    public Torre(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return true;
    }

    @Override
    public String toString() {
        return "T";
    }
}
