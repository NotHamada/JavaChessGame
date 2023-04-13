package Controller;

import Model.*;
import View.InterfaceTabuleiro;
import Model.Casa;

import java.util.*;

public class Controller {
    private final Tabuleiro tabuleiro;
    private final InterfaceTabuleiro interfaceTabuleiro;

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

            List<Casa> casasPossiveisDestino;

            boolean movimentoPossivel = false;

            while(!movimentoPossivel) {

                casasPossiveisDestino = tabuleiro.getMovimentosPossiveis(casaInicio);
                interfaceTabuleiro.pintaMovimentosPossiveis(casasPossiveisDestino);

                casaFim = interfaceTabuleiro.getCasaClicada();
                System.out.println(Casa.casaToStr(casaFim));

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
                if(tabuleiro.getFazPromocao()){
                    Peca pecaPromovida = interfaceTabuleiro.getPecaPromovida(tabuleiro.getTurno() == Cor.Brancas ?
                                    Cor.Pretas : Cor.Brancas);

                    tabuleiro.addPeca(pecaPromovida, casaFim.linha, casaFim.coluna);
                    tabuleiro.setFazPromocao(false);
                }
            } catch (MovementNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            interfaceTabuleiro.updateTabuleiro();

        }


    }
}
