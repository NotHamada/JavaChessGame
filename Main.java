public class Main {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.inicializaPosicao();

        System.out.println(tabuleiro.toString());

        try {
            tabuleiro.moverPeca("e2", "e4");
            tabuleiro.moverPeca("f1", "b5");

            tabuleiro.moverPeca("d1", "f3");
            tabuleiro.moverPeca("f3", "b3");

            tabuleiro.moverPeca("g1", "f3");

            tabuleiro.moverPeca("e1", "e2");
            tabuleiro.moverPeca("e2", "d3");

            tabuleiro.moverPeca("d7", "d5");
            tabuleiro.moverPeca("e4", "d5");
            System.out.println(tabuleiro.toString());

        } catch (MovementNotAllowedException e) {
            System.out.println(e.getMessage());
        }
    }
}