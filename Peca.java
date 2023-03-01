public abstract class Peca {
    
    private Cor jogador;
    
    public Peca(Cor jogador) {
        this.jogador = jogador;
    }

    public abstract boolean MovimentoValido(String destino, String partida);

    public Casa strToCasa(String posicao) {
        int linha = posicao.charAt(1) - '0';
        int coluna = posicao.charAt(0) - 'a';

        return new Casa(linha, coluna);
    }

    public Cor getJogador() {
        return jogador;
    }
}
