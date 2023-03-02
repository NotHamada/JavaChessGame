public class Torre extends Peca {

    public Torre(Cor jogador) {
        super(jogador);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return true;
    }

    @Override
    public String toString() {
        return "T";
    }
}
