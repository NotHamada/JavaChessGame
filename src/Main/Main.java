package Main;

import java.util.Scanner;
import javax.swing.JFrame;

import Controller.Controller;
import View.InterfaceTabuleiro;
import Model.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.inicializaPosicao();

        InterfaceTabuleiro interfaceTabuleiro = new InterfaceTabuleiro(tabuleiro);

        Controller controlador = new Controller(tabuleiro, interfaceTabuleiro);
        controlador.iniciaJogo();





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
