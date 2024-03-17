package Main;

import Controller.Controller;
import View.InterfaceTabuleiro;
import Model.Tabuleiro;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tabuleiro tabuleiro = new Tabuleiro();

        InterfaceTabuleiro interfaceTabuleiro = new InterfaceTabuleiro(tabuleiro);

        Controller controlador = new Controller(tabuleiro, interfaceTabuleiro);
        controlador.iniciaJogo();
    }
}
