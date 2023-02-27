public class Torre extends Peca {

    public Torre(int linha, int coluna, Cor jogador) {
        super(linha, coluna, jogador);
    }

    @Override
    public String toString() {
        return "T";
    }
}
