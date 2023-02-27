public class Dama extends Peca {

    public Dama(int linha, int coluna, Cor jogador) {
        super(linha, coluna, jogador);
    }

    @Override
    public String toString() {
        return "D";
    }
}
