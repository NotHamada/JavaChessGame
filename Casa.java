public class Casa {
    public int linha;
    public int coluna;

    public Casa(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean equals(Casa c) {
        return this.linha == c.linha && this.coluna == c.coluna;
    }
}
