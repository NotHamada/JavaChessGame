public class Bispo extends Peca {

    public Bispo(Cor jogador) {
        super(jogador);
    }

    @Override
    public String toString() {
        return "B";
    }

    public boolean MovimentoValido(String partida, String destino){
      Casa casaPartida = strToCasa(partida);
      Casa casaDestino = strToCasa(destino);
      
      int xPartida = casaPartida.coluna;
      int yPartida = casaPartida.coluna;
      
      int xDestino = casaDestino.coluna;
      int yDestino = casaDestino.coluna;

      int k = Math.abs(xDestino - xPartida);
      
      return yDestino + k == y || yDestino - k == y;
    }
}
