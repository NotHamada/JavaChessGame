public class Cavalo extends Peca {

    public Cavalo(Cor jogador) {
        super(jogador);
    }

    public boolean movimentoValido(Casa destino, Casa partida) {
        if (!posicaoDentroDoTabuleiro(destino))
            return false;

        int dx[] = {1, -1, 1, -1, 2, -2, 2, -2};
        int dy[] = {2, 2, -2, -2, 1, 1, -1, -1};

        for (int i = 0; i < 8; i++) {
            if (partida.linha + dy[i] == destino.linha && partida.coluna + dx[i] == destino.coluna)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "C";
    }
}
