package Controller;

import Model.MovementNotAllowedException;
import Model.Tabuleiro;
import View.InterfaceTabuleiro;
import Model.Casa;

import java.util.*;

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

            Casa casaSelecionada = interfaceTabuleiro.getCasaClicada();
            List<Casa> casasPossiveisDestino = new ArrayList<>();
            Casa casaDestino = null;
            boolean movimentoPossivel = false;

            while(!movimentoPossivel) {

                casasPossiveisDestino = tabuleiro.getMovimentosPossiveis(casaSelecionada);
                interfaceTabuleiro.pintaMovimentosPossiveis(casasPossiveisDestino);

                casaDestino = interfaceTabuleiro.getCasaClicada();

                for(Casa c : casasPossiveisDestino){
                    if(c.equals(casaDestino)){
                        movimentoPossivel = true;
                        break;
                    }
                }

                interfaceTabuleiro.resetaCores();
                if(!movimentoPossivel)
                    casaSelecionada = casaDestino;

            }


            try {
                tabuleiro.moverPeca(casaSelecionada, casaDestino);
            } catch (MovementNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            interfaceTabuleiro.updateTabuleiro();

        }


    }
}
