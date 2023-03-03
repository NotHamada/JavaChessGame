public abstract class Peca {

    private String className = this.getClass().getSimpleName();
    private Cor jogador;
    private boolean ignoraColisao;

    private Tabuleiro tabuleiro;

    public Peca(Cor jogador, Tabuleiro tabuleiro, boolean ignoraColisao) {
        this.jogador = jogador;
        this.tabuleiro = tabuleiro;
        this.ignoraColisao = ignoraColisao;
    }

    public abstract boolean movimentoValido(Casa destino, Casa partida);

    private boolean verificaColisao(Casa partida, Casa destino) {
        if (ignoraColisao)
            return true;

        // Movimento na coluna
        if (partida.linha == destino.linha) {

            int minColuna = Math.min(partida.coluna, destino.coluna);
            int maxColuna = Math.max(partida.coluna, destino.coluna);

            for (int i = minColuna + 1; i < maxColuna; i++) {
                if (tabuleiro.getPeca(partida.linha, i) != null)
                    return false;
            }
        }
        // Movimento na linha
        else if (partida.coluna == destino.coluna) {

            int minLinha = Math.min(partida.linha, destino.linha);
            int maxLinha = Math.max(partida.linha, destino.linha);

            for (int i = minLinha + 1; i < maxLinha; i++) {
                if (tabuleiro.getPeca(i, partida.coluna) != null)
                    return false;
            }
        }
        // Movimento diagonal
        else if (Math.abs(partida.linha - destino.linha) == Math.abs(partida.coluna - destino.coluna)) {
            int sinal = (partida.coluna < destino.coluna) ? 1 : -1;

            Casa min = null;
            if (partida.linha < destino.linha)
                min = new Casa(partida.linha, partida.coluna);
            else
                min = new Casa(destino.linha, destino.coluna);

            int diferenca = Math.abs(partida.linha - destino.linha);
            for (int i = 1; i < diferenca; i++) {
                int lin = min.linha + i;
                int col = min.coluna + (i * sinal);

                if (tabuleiro.getPeca(lin, col) != null)
                    return false;
            }
        }
        return true;
    }

    private boolean verificaCasaOcupada(Casa casaPartida, Casa casaDestino) {
        Peca pecaAMover = tabuleiro.getPeca(casaPartida.linha, casaPartida.coluna);
        Peca pecaDestino = tabuleiro.getPeca(casaDestino.linha, casaDestino.coluna);

        return (pecaDestino == null || pecaDestino.getJogador() != pecaAMover.getJogador());
    }

    public boolean validaMovimento(Casa partida, Casa destino) {
        Peca pecaAMover = tabuleiro.getPeca(partida.linha, partida.coluna);

        // System.out.println(pecaAMover.movimentoValido(partida, destino));
        // System.out.println(verificaCasaOcupada(partida, destino));
        // System.out.println(verificaColisao(partida, destino));

        return pecaAMover.movimentoValido(partida, destino)
                && verificaCasaOcupada(partida, destino)
                && verificaColisao(partida, destino);
    }

    public boolean dentroDoTabuleiro(Casa casa) {
        return 0 <= casa.linha && casa.linha < Tabuleiro.maxLinhas
                && 0 <= casa.coluna && casa.coluna < Tabuleiro.maxColunas;
    }

    public boolean dentroDoTabuleiro(int linha, int coluna) {
        return 0 <= linha && linha < Tabuleiro.maxLinhas
                && 0 <= coluna && coluna < Tabuleiro.maxColunas;
    }

    public Cor getJogador() {
        return jogador;
    }

    public String getClassName() {
        return className;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

}
