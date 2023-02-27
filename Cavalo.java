public class Cavalo extends Peca {

    public Cavalo(int linha, int coluna, Cor jogador) {
        super(linha, coluna, jogador);
    }

    @Override
    public String toString() {
        return "C";
    }
}
