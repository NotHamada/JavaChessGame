public class Peao extends Peca{

    public Peao(Cor jogador) {
        super(jogador);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return true;
    }

    @Override
    public String toString() {
        return "P";
    }
    
    
}
