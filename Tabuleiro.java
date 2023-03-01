import java.util.ArrayList;

public class Tabuleiro {

    public static final int maxLinhas = 8;
    public static final int maxColunas = 8;

    private ArrayList<ArrayList<Peca>> pecas;

    public Tabuleiro() {
        this.pecas = new ArrayList<ArrayList<Peca>>(maxLinhas);
        this.pecas.forEach(p -> p = new ArrayList<Peca>(maxColunas));
    }

    private void addPeca(Peca peca, int linha, int coluna) {

        if (linha < 0 || maxLinhas < linha || 0 < coluna || maxColunas < coluna) {
            System.out.println("Os valores de linha e coluna devem estar no intervalo [0," + (maxColunas - 1) + "]");
            return;
        }
        if (pecas.get(linha).get(coluna) != null)
        {
            System.out.println("Ja existe peca nesta posicao");
            return;
        }

        pecas.get(linha).set(coluna, peca);
    }

    private Casa strToCasa(String posicao) {
        int linha = posicao.charAt(1) - '0';
        int coluna = posicao.charAt(0) - 'a';

        return new Casa(linha, coluna);
    }

    public void moverPeca(String partida, String destino) throws MovementNotAllowedException {
        Casa casaPartida = strToCasa(partida);
        Casa casaDestino = strToCasa(destino);

        Peca pecaAMover = pecas.get(casaPartida.linha).get(casaPartida.coluna);
        Peca pecaDestino = pecas.get(casaDestino.linha).get(casaDestino.coluna);

        if (pecaDestino != null && pecaDestino.getJogador() == pecaAMover.getJogador())
            throw new MovementNotAllowedException();

        if (!pecaAMover.movimentoValido(casaPartida, casaDestino))
            throw new MovementNotAllowedException();

        pecas.get(casaPartida.linha).set(casaPartida.coluna, pecaAMover);
    }

    public void inicializaPosicao() {
        pecas.clear();

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
        addPeca(new Bispo(Cor.Pretas), 7,5);
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
                output += "|" + pecas.get(i).get(j).toString();
            }
            output += "|\n";
        }
        return output;
    }
}
