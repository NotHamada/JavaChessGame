import java.util.Scanner;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        InterfaceTabuleiro interfaceTabuleiro = new InterfaceTabuleiro();
        interfaceTabuleiro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfaceTabuleiro.setSize(480, 480);
        interfaceTabuleiro.setVisible(true);

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
