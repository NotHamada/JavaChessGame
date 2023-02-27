public abstract class Peca {
    
    private int linha;
    private int coluna;
    private Cor jogador;

    public int getLinha() {
        return linha;
    }
    public int getColuna() {
        return coluna;
    }
    public Cor getJogador() {
        return jogador;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    public void setJogador(Cor jogador) {
        this.jogador = jogador;
    }

    public Peca(int linha, int coluna, Cor jogador) {
        this.linha = linha;
        this.coluna = coluna;
        this.jogador = jogador;
    }
}
