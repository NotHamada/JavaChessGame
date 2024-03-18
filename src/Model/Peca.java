package Model;

public abstract class Peca {

    private final String simbolo;
    private final String className = this.getClass().getSimpleName();

    private final Cor jogador;
    private final Tabuleiro tabuleiro;
    protected int numMovimentos = 0;
    protected int numUltimoMovimentoRealizado = 0;

    public Peca(Cor jogador, Tabuleiro tabuleiro, String simbolo) {
        this.jogador = jogador;
        this.tabuleiro = tabuleiro;
        this.simbolo = simbolo;
    }

    public abstract boolean movimentoValido(Casa destino, Casa partida);

    private boolean verificaColisao(Casa partida, Casa destino) {
        if(tabuleiro.getPeca(partida) instanceof Cavalo) return true;

        if (movimentoEmColuna(partida, destino)) {
            int minColuna = Math.min(partida.coluna, destino.coluna);
            int maxColuna = Math.max(partida.coluna, destino.coluna);

            for (int i = minColuna + 1; i < maxColuna; i++) {
                if (tabuleiro.getPeca(partida.linha, i) != null)
                    return false;
            }
        }
        else if (movimentoEmLinha(partida, destino)) {
            int minLinha = Math.min(partida.linha, destino.linha);
            int maxLinha = Math.max(partida.linha, destino.linha);

            for (int i = minLinha + 1; i < maxLinha; i++) {
                if (tabuleiro.getPeca(i, partida.coluna) != null)
                    return false;
            }
        }
        else if (movimentoEmDiagonal(partida, destino)) {
            int sinalColuna = (partida.coluna < destino.coluna) ? 1 : -1;
            int sinalLinha = (partida.linha < destino.linha) ? 1 : -1;

            int diferenca = Math.abs(partida.linha - destino.linha);

            int lin = partida.linha;
            int col = partida.coluna;

            for (int i = 1; i < diferenca; i++) {
                lin += sinalLinha;
                col += sinalColuna;

                if (tabuleiro.getPeca(lin, col) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean movimentoEmLinha(Casa partida, Casa destino) {
        return partida.coluna == destino.coluna;
    }

    private boolean movimentoEmColuna(Casa partida, Casa destino) {
        return partida.linha == destino.linha;
    }

    private boolean movimentoEmDiagonal(Casa partida, Casa destino) {
        return Math.abs(partida.linha - destino.linha) == Math.abs(partida.coluna - destino.coluna);
    }

    private boolean verificaCasaOcupada(Casa casaPartida, Casa casaDestino) {
        Peca pecaAMover = tabuleiro.getPeca(casaPartida.linha, casaPartida.coluna);
        Peca pecaDestino = tabuleiro.getPeca(casaDestino.linha, casaDestino.coluna);

        return (pecaDestino == null || pecaDestino.getJogador() != pecaAMover.getJogador());
    }

    public boolean validaMovimento(Casa partida, Casa destino) {
        Peca pecaAMover = tabuleiro.getPeca(partida.linha, partida.coluna);

        return (dentroDoTabuleiro(partida) && dentroDoTabuleiro(destino)
                && verificaCasaOcupada(partida, destino)
                && verificaColisao(partida, destino)
                && pecaAMover.movimentoValido(partida, destino));
    }

    public boolean dentroDoTabuleiro(Casa casa) {
        return 0 <= casa.linha && casa.linha < Tabuleiro.maxLinhas
                && 0 <= casa.coluna && casa.coluna < Tabuleiro.maxColunas;
    }

    public boolean dentroDoTabuleiro(int linha, int coluna) {
        return 0 <= linha && linha < Tabuleiro.maxLinhas
                && 0 <= coluna && coluna < Tabuleiro.maxColunas;
    }

    public Cor getJogador() { return jogador; }

    public String getClassName() { return className; }

    public Tabuleiro getTabuleiro() { return tabuleiro; }

    public String getSimbolo() { return simbolo; }

    public Cor getCor() { return jogador; }

    public Casa getCasa() {
        for(int i = 0; i < Tabuleiro.maxLinhas; i++){
            for(int j = 0; j < Tabuleiro.maxColunas; j++){
                if(tabuleiro.getPecas()[i][j] == this) return new Casa(i, j);
            }
        }
        return null;
    }
}
