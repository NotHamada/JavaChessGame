public class Bispo extends Peca {

    public Bispo(int linha, int coluna, Cor jogador) {
        super(linha, coluna, jogador);
    }

    @Override
    public String toString() {
        return "B";
    }

    public bool MovimentoValido(String destino){
      int x = linha;
      int y = coluna;

      int xDestino;
      int yDestino;

      int k = abs(xDestino - x);
      
      return yDestino + k == y || yDestino - k == y;
    }
}
