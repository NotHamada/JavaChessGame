package Model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    public static final int maxLinhas = 8;
    public static final int maxColunas = 8;

    private final Peca[][] pecas;
    protected int contadorMovimentos;
    protected Cor turno;
    protected String mensagemDeVitoria;
    private boolean fazPromocao;
    private boolean enPassant;

    public Tabuleiro() {
        pecas = new Peca[maxLinhas][maxColunas];
        resetaVariaveis();
    }

    private void resetaVariaveis() {
        contadorMovimentos = 0;
        mensagemDeVitoria = null;
        turno = Cor.Brancas;
    }

    public void addPeca(Peca peca, int linha, int coluna) {
        if (linha < 0 || maxLinhas < linha || coluna < 0 || maxColunas < coluna) {
            System.out.println("Os valores de linha e coluna devem estar no intervalo [0," + (maxColunas - 1) + "]");
            return;
        }
        pecas[linha][coluna] = peca;
    }

    public void fazJogada(Casa partida, Casa destino) throws MovementNotAllowedException {
        Peca pecaAMover = pecas[partida.linha][partida.coluna];
        Peca pecaCapturada = null;

        TipoMovimento tipoMovimento = classificaMovimento(partida, destino);

        switch (tipoMovimento) {
            case Simples -> {
                pecaCapturada = capturaPeca(destino);
                movePeca(partida, destino);
            }
            case Roque -> {
                Rei rei = (Rei) pecas[partida.linha][partida.coluna];
                List<Casa> casasRoque = rei.roque(partida, destino);

                Casa casaAntigaRei = casasRoque.get(0);
                Casa casaAntigaTorre = casasRoque.get(1);
                Casa casaNovaRei = casasRoque.get(2);
                Casa casaNovaTorre = casasRoque.get(3);

                movePeca(casaAntigaRei, casaNovaRei);
                movePeca(casaAntigaTorre, casaNovaTorre);
            }
            case EnPassant -> {
                Casa casaPeaoCapturado = Peao.enPassant(partida, destino);

                pecaCapturada = capturaPeca(casaPeaoCapturado);
                movePeca(partida, destino);
                enPassant = true;
            }
            case PromocaoPeao -> {
                fazPromocao = true;

                pecaCapturada = capturaPeca(destino);
                movePeca(partida, destino);
            }
        }
        contadorMovimentos++;

        if (jogadorAtualEstaEmXeque()) {
            voltarMovimento(partida, destino, pecaAMover, pecaCapturada);
            contadorMovimentos--;
            throw new MovementNotAllowedException("O movimento deixa o rei em cheque");
        }
        this.turno = (this.turno == Cor.Brancas) ? Cor.Pretas : Cor.Brancas;

        verificaXequeMate();
    }

    public List<Casa> getMovimentosPossiveis(Casa casaDaPeca) {

        List<Casa> casasPossiveis = new ArrayList<>();
        Peca peca = pecas[casaDaPeca.linha][casaDaPeca.coluna];

        if (peca == null || peca.getCor() != turno)
            return casasPossiveis;

        boolean estaEmXeque = jogadorAtualEstaEmXeque();
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                Casa casa = new Casa(i, j);
                if (!peca.validaMovimento(casaDaPeca, casa))
                    continue;
                
                if (!estaEmXeque || (estaEmXeque && movimentoTiraReideXeque(casaDaPeca, casa)))
                    casasPossiveis.add(new Casa(i, j));
            }
        }
        return casasPossiveis;
    }

    private Peca capturaPeca(Casa casaCapturada) {
        Peca p = pecas[casaCapturada.linha][casaCapturada.coluna];
        pecas[casaCapturada.linha][casaCapturada.coluna] = null;
        return p;
    }

    private void movePeca(Casa casaPartida, Casa casaDestino) {
        Peca pecaAMover = pecas[casaPartida.linha][casaPartida.coluna];

        pecaAMover.numMovimentos++;
        pecaAMover.numUltimoMovimentoRealizado = contadorMovimentos;

        pecas[casaDestino.linha][casaDestino.coluna] = pecaAMover;
        pecas[casaPartida.linha][casaPartida.coluna] = null;
    }

    private TipoMovimento classificaMovimento(Casa partida, Casa destino) {
        if (moveIsRoque(partida, destino))
            return TipoMovimento.Roque;

        if (moveIsEnPassant(partida, destino))
            return TipoMovimento.EnPassant;

        if (moveIsPromotion(partida, destino))
            return TipoMovimento.PromocaoPeao;
            
        return TipoMovimento.Simples;
    }

    private boolean moveIsRoque(Casa partida, Casa destino) {
        Peca toMove = pecas[partida.linha][partida.coluna];
        return toMove instanceof Rei && ((Rei) toMove).verificaRoque(partida, destino);
    }
    private boolean moveIsEnPassant(Casa partida, Casa destino) {
        Peca toMove = pecas[partida.linha][partida.coluna];
        return toMove instanceof Peao && ((Peao) toMove).verificaEnPassant(partida, destino);
    }
    private boolean moveIsPromotion(Casa partida, Casa destino) {
        Peca toMove = pecas[partida.linha][partida.coluna];
        return toMove instanceof Peao && (destino.linha == Tabuleiro.maxLinhas -1 || destino.linha == 0);
    }

    // necessita que o rei do jogador atual está em xeque e o movimento é valido.
    private boolean movimentoTiraReideXeque(Casa casaPartida, Casa casaDestino) {

        Peca pecaAMover = pecas[casaPartida.linha][casaPartida.coluna];

        pecas[casaPartida.linha][casaPartida.coluna].numMovimentos++;
        Peca pecaCapturada = pecas[casaDestino.linha][casaDestino.coluna];
        pecas[casaPartida.linha][casaPartida.coluna] = null;
        pecas[casaDestino.linha][casaDestino.coluna] = pecaAMover;
        contadorMovimentos++;

        if (jogadorAtualEstaEmXeque()) {
            voltarMovimento(casaPartida, casaDestino, pecaAMover, pecaCapturada);   
            return false;
        } else {
            voltarMovimento(casaPartida, casaDestino, pecaAMover, pecaCapturada);
            return true;
        }
    }

    protected boolean estaAmeacado(Casa casa, Cor jogador) {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                if (pecas[i][j] != null
                        && pecas[i][j].getJogador() != jogador
                        && pecas[i][j].validaMovimento(new Casa(i, j), casa)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jogadorAtualEstaEmXeque() {
        try {
            Casa casaDoRei = getCasaDoReiDoJogadorAtual();
            return estaAmeacado(casaDoRei, this.turno);
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(0);
            return false;
        }
    }

    private Casa getCasaDoReiDoJogadorAtual() throws Exception {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                Peca p = pecas[i][j];
                if (p instanceof Rei && p.getJogador() == this.turno) {
                    return new Casa(i, j);
                }
            }
        }
        throw new Exception("Rei do jogador atual não encontrado");
    }

    private void verificaXequeMate() {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                if (pecaEhDoJogadorAtual(pecas[i][j]) && jogadorAtualPossuiMovimentoValido(pecas[i][j], new Casa(i, j)))
                    return;
            }
        }
        mensagemDeVitoria = (this.turno == Cor.Brancas) ? "Vitoria das Pretas" : "Vitoria das Brancas";
        System.out.println(mensagemDeVitoria);
        System.out.println(this);
        System.exit(1);
    }

    private boolean pecaEhDoJogadorAtual(Peca p) {
        return p != null && p.getJogador() == turno;
    }

    private boolean jogadorAtualPossuiMovimentoValido(Peca peca, Casa casaDaPeca) {
        boolean estaEmXeque = jogadorAtualEstaEmXeque();

        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                boolean moveIsValid = peca.validaMovimento(casaDaPeca, new Casa(i, j));
                if (!moveIsValid)
                    continue;

                if (!estaEmXeque)
                    return true;

                if (movimentoTiraReideXeque(casaDaPeca, new Casa(i, j)))
                    return true;
            }
        }
        return false;
    }

    private void voltarMovimento(Casa casaPartida, Casa casaDestino, Peca pecaAMover, Peca pecaCapturada) {
        pecas[casaDestino.linha][casaDestino.coluna].numMovimentos--;

        pecas[casaDestino.linha][casaDestino.coluna] = pecaCapturada;
        pecas[casaPartida.linha][casaPartida.coluna] = pecaAMover;

        contadorMovimentos--;
    }

    public void inicializaPosicao() {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++)
                pecas[i][j] = null;
        }
        resetaVariaveis();

        addPeca(new Torre(Cor.Pretas, this), 0, 0);
        addPeca(new Cavalo(Cor.Pretas, this), 0, 1);
        addPeca(new Bispo(Cor.Pretas, this), 0, 2);
        addPeca(new Dama(Cor.Pretas, this), 0, 3);
        addPeca(new Rei(Cor.Pretas, this), 0, 4);
        addPeca(new Bispo(Cor.Pretas, this), 0, 5);
        addPeca(new Cavalo(Cor.Pretas, this), 0, 6);
        addPeca(new Torre(Cor.Pretas, this), 0, 7);

        addPeca(new Torre(Cor.Brancas, this), 7, 0);
        addPeca(new Cavalo(Cor.Brancas, this), 7, 1);
        addPeca(new Bispo(Cor.Brancas, this), 7, 2);
        addPeca(new Dama(Cor.Brancas, this), 7, 3);
        addPeca(new Rei(Cor.Brancas, this), 7, 4);
        addPeca(new Bispo(Cor.Brancas, this), 7, 5);
        addPeca(new Cavalo(Cor.Brancas, this), 7, 6);
        addPeca(new Torre(Cor.Brancas, this), 7, 7);

        addPeca(new Peao(Cor.Brancas, this), 6, 0);
        addPeca(new Peao(Cor.Brancas, this), 6, 1);
        addPeca(new Peao(Cor.Brancas, this), 6, 2);
        addPeca(new Peao(Cor.Brancas, this), 6, 3);
        addPeca(new Peao(Cor.Brancas, this), 6, 4);
        addPeca(new Peao(Cor.Brancas, this), 6, 5);
        addPeca(new Peao(Cor.Brancas, this), 6, 6);
        addPeca(new Peao(Cor.Brancas, this), 6, 7);

        addPeca(new Peao(Cor.Pretas, this), 1, 0);
        addPeca(new Peao(Cor.Pretas, this), 1, 1);
        addPeca(new Peao(Cor.Pretas, this), 1, 2);
        addPeca(new Peao(Cor.Pretas, this), 1, 3);
        addPeca(new Peao(Cor.Pretas, this), 1, 4);
        addPeca(new Peao(Cor.Pretas, this), 1, 5);
        addPeca(new Peao(Cor.Pretas, this), 1, 6);
        addPeca(new Peao(Cor.Pretas, this), 1, 7);
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();
        for (int i = maxLinhas - 1; i >= 0; i--) {

            output.append((char) ('1' + i));

            for (int j = 0; j < maxColunas; j++) {
                if (pecas[i][j] == null)
                    output.append("| ");
                else
                    output.append("|").append(pecas[i][j].toString());
            }
            output.append("|\n");
        }
        output.append(" |A|B|C|D|E|F|G|H|\n");
        return output.toString();
    }

    public Peca[][] getPecas() { return pecas; }
    public Peca getPeca(int linha, int coluna) { return pecas[linha][coluna]; }
    public Peca getPeca(Casa casa) { return pecas[casa.linha][casa.coluna];}
    public Peca getPeca(String str) {
        Casa casa = Casa.strToCasa(str);
        return pecas[casa.linha][casa.coluna];
    }

    public boolean getFazPromocao() { return fazPromocao; }
    public void setFazPromocao(boolean b) { fazPromocao = b; }
    
    public boolean getEnPassant() { return enPassant;}
    public void setEnPassant(boolean b) { enPassant = b;}

    public Cor getTurno() { return turno; }

    public boolean jogoAcabou() { return mensagemDeVitoria != null; }
}
