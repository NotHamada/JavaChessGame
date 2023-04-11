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

            Casa casaInicio = interfaceTabuleiro.getCasaClicada();
            Casa casaFim = null;

            List<Casa> casasPossiveisDestino = new ArrayList<>();

            boolean movimentoPossivel = false;

            while(!movimentoPossivel) {

                casasPossiveisDestino = tabuleiro.getMovimentosPossiveis(casaInicio);
                interfaceTabuleiro.pintaMovimentosPossiveis(casasPossiveisDestino);

                casaFim = interfaceTabuleiro.getCasaClicada();
                System.out.println(tabuleiro.casaToStr(casaFim));

                for(Casa c : casasPossiveisDestino){
                    if(c.equals(casaFim)){
                        movimentoPossivel = true;
                        break;
                    }
                }

                interfaceTabuleiro.resetaCores();
                if(!movimentoPossivel)
                    casaInicio = casaFim;

            }


            try {
                tabuleiro.moverPeca(casaInicio, casaFim);
            } catch (MovementNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            interfaceTabuleiro.updateTabuleiro();

        }


    }
}
