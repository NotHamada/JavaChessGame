public class Torre extends Peca {

    public Torre(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false, "T");
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return partida.coluna == destino.coluna || partida.linha == destino.linha;
    }
}
