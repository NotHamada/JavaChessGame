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

    private boolean verificaColisao(Casa partida, Casa destino) {

        // Movimento na coluna
        if (partida.linha == destino.linha) {

            int minColuna = Math.min(partida.coluna, destino.coluna);
            int maxColuna = Math.max(partida.coluna, destino.coluna);

            for (int i = minColuna + 1; i < maxColuna; i++) {
                if (pecas[partida.linha][i] != null)
                    return false;
            }
        }
        // Movimento na linha
        else if (partida.coluna == destino.coluna) {

            int minLinha = Math.min(partida.linha, destino.linha);
            int maxLinha = Math.max(partida.linha, destino.linha);

            for (int i = minLinha + 1; i < maxLinha; i++) {
                if (pecas[i][partida.coluna] != null)
                    return false;
            }
        }
        // Movimento diagonal
        else if (Math.abs(partida.linha - destino.linha) == Math.abs(partida.coluna - destino.coluna))
        {
            Casa min = new Casa(Math.min(partida.linha, destino.linha), Math.min(partida.coluna, destino.coluna));
            int diferenca = Math.abs(partida.linha - destino.linha);

            for (int i = 1; i < diferenca; i++) {
                if (pecas[min.linha + i][min.coluna + i] != null)
                    return false;
            }
        }
        return true;
    }

    public void moverPeca(String partida, String destino) throws MovementNotAllowedException {
        Casa casaPartida = strToCasa(partida);
        Casa casaDestino = strToCasa(destino);

        Peca pecaAMover = pecas[casaPartida.linha][casaPartida.coluna];
        Peca pecaDestino = pecas[casaDestino.linha][casaDestino.coluna];

        if (pecaDestino != null && pecaDestino.getJogador() == pecaAMover.getJogador())
            throw new MovementNotAllowedException(pecaAMover.getClassName(), partida, destino);

        if (!pecaAMover.movimentoValido(casaPartida, casaDestino) || verificaColisao(casaPartida, casaDestino))
            throw new MovementNotAllowedException(pecaAMover.getClassName(), partida, destino);
        
        pecas[casaPartida.linha][casaPartida.coluna] = null;
        pecas[casaDestino.linha][casaDestino.coluna] = pecaAMover;
    }

    public void inicializaPosicao() {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++)
                pecas[i][j] = null;
        }

        addPeca(new Torre(Cor.Brancas), 0, 0);
        addPeca(new Cavalo(Cor.Brancas), 0, 1);
        addPeca(new Bispo(Cor.Brancas), 0, 2);
        addPeca(new Dama(Cor.Brancas), 0, 3);
        addPeca(new Rei(Cor.Brancas), 0, 4);
        addPeca(new Bispo(Cor.Brancas), 0, 5);
        addPeca(new Cavalo(Cor.Brancas), 0, 6);
        addPeca(new Torre(Cor.Brancas), 0, 7);

        addPeca(new Torre(Cor.Pretas), 7, 0);
        addPeca(new Cavalo(Cor.Pretas), 7, 1);
        addPeca(new Bispo(Cor.Pretas), 7, 2);
        addPeca(new Dama(Cor.Pretas), 7, 3);
        addPeca(new Rei(Cor.Pretas), 7, 4);
        addPeca(new Bispo(Cor.Pretas), 7, 5);
        addPeca(new Cavalo(Cor.Pretas), 7, 6);
        addPeca(new Torre(Cor.Pretas), 7, 7);

        addPeca(new Peao(Cor.Pretas), 6, 0);
        addPeca(new Peao(Cor.Pretas), 6, 1);
        addPeca(new Peao(Cor.Pretas), 6, 2);
        addPeca(new Peao(Cor.Pretas), 6, 3);
        addPeca(new Peao(Cor.Pretas), 6, 4);
        addPeca(new Peao(Cor.Pretas), 6, 5);
        addPeca(new Peao(Cor.Pretas), 6, 6);
        addPeca(new Peao(Cor.Pretas), 6, 7);

        addPeca(new Peao(Cor.Brancas), 1, 0);
        addPeca(new Peao(Cor.Brancas), 1, 1);
        addPeca(new Peao(Cor.Brancas), 1, 2);
        addPeca(new Peao(Cor.Brancas), 1, 3);
        addPeca(new Peao(Cor.Brancas), 1, 4);
        addPeca(new Peao(Cor.Brancas), 1, 5);
        addPeca(new Peao(Cor.Brancas), 1, 6);
        addPeca(new Peao(Cor.Brancas), 1, 7);
    }

    @Override
    public String toString() {

        String output = "";
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                if (pecas[i][j] == null)
                    output += "| ";
                else
                    output += "|" + pecas[i][j].toString();
            }
            output += "|\n";
        }
        return output;
    }
}
