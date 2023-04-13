package Model;

public class Torre extends Peca {

    public Torre(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, "T");
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return partida.coluna == destino.coluna || partida.linha == destino.linha;
    }
}
