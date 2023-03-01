public abstract class Peca {
    
    private Cor jogador;
    
    public Peca(Cor jogador) {
        this.jogador = jogador;
    }

    public abstract boolean movimentoValido(Casa destino, Casa partida);

    public boolean posicaoDentroDoTabuleiro(Casa casa) {
        return 0 <= casa.linha && casa.linha <= 7 && 0 <= casa.coluna && casa.coluna <= 7;
    }

    public Cor getJogador() {
        return jogador;
    }
}
