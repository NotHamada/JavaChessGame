public class Dama extends Peca {

    public Dama(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        Torre torre = new Torre(this.getJogador(), this.getTabuleiro());
        Bispo bispo = new Bispo(this.getJogador(), this.getTabuleiro());

        return torre.movimentoValido(partida, destino) || bispo.movimentoValido(partida, destino);
    }

    @Override
    public String toString() {
        return "D";
    }
}
