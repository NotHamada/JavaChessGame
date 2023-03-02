public class Main {
    public static void main(String[] args) {
        
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.inicializaPosicao();

        System.out.println(tabuleiro.toString());

        try {
            tabuleiro.moverPeca("b1", "c3");
            System.out.println(tabuleiro.toString());
            
        } catch (MovementNotAllowedException e) {
            System.out.println(e.getMessage());
        }
    }
}