package Controller;
import Model.*;
import Model.Cor;
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
        inicializaInterface();
        while(!tabuleiro.jogoAcabou()){
            Casa casaInicio = interfaceTabuleiro.getCasaClicada();
            Casa casaFim = null;
            List<Casa> casasPossiveisDestino;
            boolean movimentoPossivel = false;
            while(!movimentoPossivel) {
                casasPossiveisDestino = tabuleiro.getMovimentosPossiveis(casaInicio);
                interfaceTabuleiro.marcarMovimentosPossiveis(casasPossiveisDestino);
                casaFim = interfaceTabuleiro.getCasaClicada();
                System.out.println(Casa.casaToStr(casaFim));
                for(Casa c : casasPossiveisDestino){
                    if(c.equals(casaFim)){
                        movimentoPossivel = true;
                        break;
                    }
                }
                interfaceTabuleiro.removerMarcacoesMovimentosPossiveis(casasPossiveisDestino);
                if(!movimentoPossivel)
                    casaInicio = casaFim;
            }
            try {
                tabuleiro.fazJogada(casaInicio, casaFim);
                if(tabuleiro.getFazPromocao()){
                    Peca pecaPromovida = new Dama(tabuleiro.getTurno() == Cor.Brancas ? Cor.Pretas : Cor.Brancas, tabuleiro);
                    tabuleiro.addPeca(pecaPromovida, casaFim.linha, casaFim.coluna);
                    tabuleiro.setFazPromocao(false);
                }
            } catch (MovementNotAllowedException e) {
                System.out.println(e.getMessage());
            }
           updateMovimentoInterface(casaInicio, casaFim);
        }
    }

    private void updateMovimentoInterface(Casa casaInicio, Casa casaFim) {
        if (tabuleiro.getEnPassant()) {
            updateCasaInterface(casaFim.linha - 1, casaFim.coluna);
            updateCasaInterface(casaFim.linha + 1, casaFim.coluna);
            tabuleiro.setEnPassant(false);
        }

        updateCasaInterface(casaInicio.linha, casaInicio.coluna);
        updateCasaInterface(casaFim.linha, casaFim.coluna);
    }
    private void updateCasaInterface(final int linha, final int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        String simboloPeca = null;
        Cor corPeca = null;
        if (peca != null) {
            simboloPeca = peca.getSimbolo();
            corPeca = peca.getCor();
        }
        interfaceTabuleiro.updateCasa(linha, coluna, simboloPeca , corPeca);
    }
    private void inicializaInterface() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                updateCasaInterface(i, j);
            }
        }
    }
}