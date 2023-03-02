public class Rei extends Peca {

    public Rei(Cor jogador) {
        super(jogador);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return true;
    }

    @Override
    public String toString() {
        return "R";
    }
}
