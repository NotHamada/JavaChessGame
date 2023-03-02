public class Dama extends Peca {

    public Dama(Cor jogador) {
        super(jogador);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return true;
    }

    @Override
    public String toString() {
        return "D";
    }
}
