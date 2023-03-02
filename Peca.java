public abstract class Peca {
    
    private Cor jogador;
    
    public Peca(Cor jogador) {
        this.jogador = jogador;
    }

    public abstract boolean movimentoValido(Casa destino, Casa partida);

    public boolean dentroDoTabuleiro(Casa casa) {
        return 0 <= casa.linha && casa.linha <= 7 && 0 <= casa.coluna && casa.coluna <= 7;
    }
    public boolean dentroDoTabuleiro(int linha, int coluna) {
        return 0 <= linha && linha <= 7 && 0 <= coluna && coluna <= 7;
    }

    public Cor getJogador() {
        return jogador;
    }
}
