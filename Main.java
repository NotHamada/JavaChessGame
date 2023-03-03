public class Main {
    public static void main(String[] args) {
        
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.inicializaPosicao();

        System.out.println(tabuleiro.toString());

        try {
            tabuleiro.moverPeca("e2", "e4");
            tabuleiro.moverPeca("g1", "f3");
            tabuleiro.moverPeca("f1", "b5");
            System.out.println(tabuleiro.toString());
            
        } catch (MovementNotAllowedException e) {
            System.out.println(e.getMessage());
        }
    }
}