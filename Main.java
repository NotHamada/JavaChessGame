import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.inicializaPosicao();

        System.out.println(tabuleiro.toString());

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String from = scan.next();
            String to = scan.next();

            try {
                tabuleiro.moverPeca(from, to);
            } catch (MovementNotAllowedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(tabuleiro.toString());

        }
        scan.close();

    }
}
