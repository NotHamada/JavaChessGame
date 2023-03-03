public class Tabuleiro {

    public static final int maxLinhas = 8;
    public static final int maxColunas = 8;

    private Peca[][] pecas;

    public Tabuleiro() {
        pecas = new Peca[maxLinhas][maxColunas];
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

    private Casa strToCasa(String posicao) {
        int linha = posicao.charAt(1) - '1';
        int coluna = posicao.charAt(0) - 'a';

        return new Casa(linha, coluna);
    }

    public void moverPeca(String partida, String destino) throws MovementNotAllowedException {
        Casa casaPartida = strToCasa(partida);
        Casa casaDestino = strToCasa(destino);

        Peca pecaAMover = pecas[casaPartida.linha][casaPartida.coluna];
        if (pecaAMover == null || !pecaAMover.validaMovimento(casaPartida, casaDestino))
            throw new MovementNotAllowedException(pecaAMover.getClassName(), partida, destino);

        pecas[casaPartida.linha][casaPartida.coluna] = null;
        pecas[casaDestino.linha][casaDestino.coluna] = pecaAMover;
    }

    public void inicializaPosicao() {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++)
                pecas[i][j] = null;
        }

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

    public Peca[][] getPecas() {
        return pecas;
    }

    public Peca getPeca(int linha, int coluna) {
        return pecas[linha][coluna];
    }

}
