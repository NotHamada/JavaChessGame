public class Bispo extends Peca {

    public Bispo(Cor jogador) {
        super(jogador);
    }

    @Override
    public String toString() {
        return "B";
    }

    public boolean MovimentoValido(String destino, String partida){
      int linha = partida.charAt(1) - '0';
      int y = coluna;

      int xDestino;
      int yDestino;

      int k = abs(xDestino - x);
      
      return yDestino + k == y || yDestino - k == y;
    }
}
