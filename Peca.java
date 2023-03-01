public abstract class Peca {
    
    private Cor jogador;
    
    public Peca(Cor jogador) {
        this.jogador = jogador;
    }

    public Cor getJogador() {
        return jogador;
    }
}
