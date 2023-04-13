package Model;

public class Cavalo extends Peca {

    public Cavalo(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, "C");
    }

    public boolean movimentoValido(Casa destino, Casa partida) {
        int dx[] = { 1, -1, 1, -1, 2, -2, 2, -2 };
        int dy[] = { 2, 2, -2, -2, 1, 1, -1, -1 };

        for (int i = 0; i < 8; i++) {
            if (dentroDoTabuleiro(partida.linha + dy[i], partida.coluna + dx[i])
                    && partida.linha + dy[i] == destino.linha
                    && partida.coluna + dx[i] == destino.coluna)
                return true;
        }
        return false;
    }
}
