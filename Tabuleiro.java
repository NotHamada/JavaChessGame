import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    public static final int maxLinhas = 8;
    public static final int maxColunas = 8;

    protected Peca[][] pecas;
    protected int contadorMovimentos;
    protected Cor turno;
    protected String mensagemDeVitoria;

    public Tabuleiro() {
        pecas = new Peca[maxLinhas][maxColunas];
        resetaVariaveis();
    }
    
    private void resetaVariaveis() {
        contadorMovimentos = 0;
        mensagemDeVitoria = null;
        turno = Cor.Brancas;
    }

    private void addPeca(Peca peca, int linha, int coluna) {

        if (linha < 0 || maxLinhas < linha || coluna < 0 || maxColunas < coluna) {
            System.out.println("Os valores de linha e coluna devem estar no intervalo [0," + (maxColunas - 1) + "]");
            return;
        }
        if (pecas[linha][coluna] != null) {
            System.out.println("Ja existe peca nesta posicao");
            return;
        }

        pecas[linha][coluna] = peca;
    }

    public Casa strToCasa(String posicao) {
        int linha = posicao.charAt(1) - '1';
        int coluna = posicao.charAt(0) - 'a';

        return new Casa(linha, coluna);
    }
    public String casaToStr(Casa casa) {
        String str = String.valueOf((char) (casa.coluna + 'a')) + String.valueOf(casa.linha + 1);
        
        return str;
    }

    public void moverPeca(String partida, String destino) throws MovementNotAllowedException {
        
        Casa casaPartida = strToCasa(partida);
        Casa casaDestino = strToCasa(destino);

        Peca pecaAMover = pecas[casaPartida.linha][casaPartida.coluna];

        if (mensagemDeVitoria != null)
            throw new MovementNotAllowedException("Esta partida ja tem um vencedor");

        if (pecaAMover == null)
            throw new MovementNotAllowedException("casa vazia", partida, destino);

        if (pecaAMover.getJogador() != turno)
            throw new MovementNotAllowedException("Peca nao pertence ao jogador deste turno");

        if (!pecaAMover.validaMovimento(casaPartida, casaDestino))
            throw new MovementNotAllowedException(pecaAMover.getClassName(), partida, destino);

        pecas[casaPartida.linha][casaPartida.coluna].numMovimentos++;

        pecas[casaPartida.linha][casaPartida.coluna] = null;
        pecas[casaDestino.linha][casaDestino.coluna] = pecaAMover;

        contadorMovimentos++;

        if (estaEmXeque()) {
            voltarMovimento(casaPartida, casaDestino, pecaAMover);
            throw new MovementNotAllowedException("O movimento deixa o rei em cheque");
        }

        this.turno = (this.turno == Cor.Brancas) ? Cor.Pretas : Cor.Brancas;

        verificaXequeMate();
    }

    
    @Override
    public String toString() {

        String output = "";
        for (int i = maxLinhas - 1; i >= 0; i--) {
            
            output += String.valueOf((char) ('1' + i));
            
            for (int j = 0; j < maxColunas; j++) {
                if (pecas[i][j] == null)
                    output += "| ";
                else
                    output += "|" + pecas[i][j].toString();
            }
            output += "|\n";
        }
        output += " |A|B|C|D|E|F|G|H|\n";
        return output;
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

    protected boolean estaAmeacado(String str, Cor cor) {
        Casa casa = strToCasa(str);
        return estaAmeacado(casa, cor);
    }
    
    private boolean estaEmXeque() {
        /* Verifica se o rei do jogador no turno atual está em xeque */
        
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                if (pecas[i][j] instanceof Rei && pecas[i][j].getJogador() == this.turno) {
                    Casa casa = new Casa(i, j);
                    return estaAmeacado(casa, this.turno);
                }
            }
        }
        return false;
    }
    
    private void verificaXequeMate() {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                if (pecas[i][j] != null && pecas[i][j].getJogador() == turno) {
                    if (possuiAlgumMovimentoValido(pecas[i][j], new Casa(i, j)))
                        return;
                }
            }
        }
        mensagemDeVitoria = (this.turno == Cor.Brancas) ? "Vitoria das Pretas" : "Vitoria das Brancas";
        System.out.println(mensagemDeVitoria);
        System.out.println(toString()); 
        System.exit(1);
    }

    private boolean possuiAlgumMovimentoValido(Peca peca, Casa casaDaPeca) {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                if (peca.validaMovimento(casaDaPeca, new Casa(i, j)) && !estaEmXeque())
                    return true;
            }
        }
        return false;
    }
    
    public List<Casa> casasDoRei(Casa casaInicial) {
        List<Casa> casas = new ArrayList<Casa>();
        
        if (casaInicial.linha + 1 <= 7) {
            var superior = new Casa(casaInicial.linha + 1, casaInicial.coluna);
            casas.add(superior);
            
            if (casaInicial.coluna - 1 >= 0) {
                var superiorEsquerda = new Casa(casaInicial.linha + 1, casaInicial.coluna - 1);
                casas.add(superiorEsquerda);
            }
            
            if (casaInicial.coluna + 1 <= 7) {
                var superiorDireita = new Casa(casaInicial.linha + 1, casaInicial.coluna + 1);
                casas.add(superiorDireita);
            }
            
        }
        
        if (casaInicial.coluna - 1 >= 0) {
            var esquerda = new Casa(casaInicial.linha, casaInicial.coluna - 1);
            casas.add(esquerda);
        }
        
        if (casaInicial.coluna + 1 <= 7) {
            var direita = new Casa(casaInicial.linha, casaInicial.coluna + 1);
            casas.add(direita);
        }
        
        if (casaInicial.linha - 1 >= 0) {
            var inferior = new Casa(casaInicial.linha - 1, casaInicial.coluna);

            if (casaInicial.coluna - 1 >= 0) {
                var inferiorEsquerda = new Casa(casaInicial.linha - 1, casaInicial.coluna - 1);
                casas.add(inferiorEsquerda);
            }

            if (casaInicial.coluna + 1 <= 7) {
                var inferiorDireita = new Casa(casaInicial.linha - 1, casaInicial.coluna + 1);
                casas.add(inferiorDireita);
            }
            
            casas.add(inferior);
        }

        return casas;
    }
    
    public void voltarMovimento(Casa casaPartida, Casa casaDestino, Peca pecaAMover) {
        pecas[casaDestino.linha][casaDestino.coluna].numMovimentos--;
        
        pecas[casaDestino.linha][casaDestino.coluna] = null;
        pecas[casaPartida.linha][casaPartida.coluna] = pecaAMover;
        
        contadorMovimentos--;
    }
    
    public void inicializaPosicao() {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++)
                pecas[i][j] = null;
        }
        resetaVariaveis();

        addPeca(new Torre(Cor.Brancas, this), 0, 0);
        addPeca(new Cavalo(Cor.Brancas, this), 0, 1);
        addPeca(new Bispo(Cor.Brancas, this), 0, 2);
        addPeca(new Dama(Cor.Brancas, this), 0, 3);
        addPeca(new Rei(Cor.Brancas, this), 0, 4);
        addPeca(new Bispo(Cor.Brancas, this), 0, 5);
        addPeca(new Cavalo(Cor.Brancas, this), 0, 6);
        addPeca(new Torre(Cor.Brancas, this), 0, 7);

        addPeca(new Torre(Cor.Pretas, this), 7, 0);
        addPeca(new Cavalo(Cor.Pretas, this), 7, 1);
        addPeca(new Bispo(Cor.Pretas, this), 7, 2);
        addPeca(new Dama(Cor.Pretas, this), 7, 3);
        addPeca(new Rei(Cor.Pretas, this), 7, 4);
        addPeca(new Bispo(Cor.Pretas, this), 7, 5);
        addPeca(new Cavalo(Cor.Pretas, this), 7, 6);
        addPeca(new Torre(Cor.Pretas, this), 7, 7);

        addPeca(new Peao(Cor.Pretas, this), 6, 0);
        addPeca(new Peao(Cor.Pretas, this), 6, 1);
        addPeca(new Peao(Cor.Pretas, this), 6, 2);
        addPeca(new Peao(Cor.Pretas, this), 6, 3);
        addPeca(new Peao(Cor.Pretas, this), 6, 4);
        addPeca(new Peao(Cor.Pretas, this), 6, 5);
        addPeca(new Peao(Cor.Pretas, this), 6, 6);
        addPeca(new Peao(Cor.Pretas, this), 6, 7);

        addPeca(new Peao(Cor.Brancas, this), 1, 0);
        addPeca(new Peao(Cor.Brancas, this), 1, 1);
        addPeca(new Peao(Cor.Brancas, this), 1, 2);
        addPeca(new Peao(Cor.Brancas, this), 1, 3);
        addPeca(new Peao(Cor.Brancas, this), 1, 4);
        addPeca(new Peao(Cor.Brancas, this), 1, 5);
        addPeca(new Peao(Cor.Brancas, this), 1, 6);
        addPeca(new Peao(Cor.Brancas, this), 1, 7);
    }

    public Peca[][] getPecas() {
        return pecas;
    }
    
    public Peca getPeca(int linha, int coluna) {
        return pecas[linha][coluna];
    }
    
    public Peca getPeca(Casa casa) {
        return pecas[casa.linha][casa.coluna];
    }
    
    public Peca getPeca(String str) {
        Casa casa = strToCasa(str);
        return pecas[casa.linha][casa.coluna];
    }

}
