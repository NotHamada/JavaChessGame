package Controller;

import Model.MovementNotAllowedException;
import Model.Tabuleiro;
import View.InterfaceTabuleiro;
import Model.Casa;
public class Controller {
    private Tabuleiro tabuleiro;
    private InterfaceTabuleiro interfaceTabuleiro;

    public Controller(Tabuleiro tabuleiro, InterfaceTabuleiro interfaceTabuleiro){
        this.tabuleiro = tabuleiro;
        this.interfaceTabuleiro = interfaceTabuleiro;
    }

    public void iniciaJogo() throws InterruptedException {
        tabuleiro.inicializaPosicao();
        interfaceTabuleiro.updateTabuleiro();


        while(!tabuleiro.jogoAcabou()){
            Casa from = interfaceTabuleiro.getCasaClicada();
            Casa to = interfaceTabuleiro.getCasaClicada();

            try {
                tabuleiro.moverPeca(from, to);
            } catch (MovementNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            interfaceTabuleiro.updateTabuleiro();

        }


    }
}
