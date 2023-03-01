import java.util.ArrayList;

public class Tabuleiro {

    public static final int maxLinhas = 8;
    public static final int maxColunas = 8;

    private ArrayList<ArrayList<Peca>> pecas;

    public Tabuleiro() {
        this.pecas = new ArrayList<ArrayList<Peca>>(maxLinhas);
        this.pecas.forEach(p -> p = new ArrayList<Peca>(maxColunas));
    }

    public void addPeca(Peca peca, int linha, int coluna) {

        if (linha < 0 || linha > maxLinhas || coluna < 0 || coluna > maxColunas) {
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

    public void inicializaPosicao() {
        pecas.clear();

        this.addPeca(new Torre(Cor.Brancas), 0, 0);
        this.addPeca(new Cavalo(Cor.Brancas), 0, 1);
        this.addPeca(new Bispo(Cor.Brancas), 0, 2);
        this.addPeca(new Dama(Cor.Brancas), 0, 3);
        this.addPeca(new Rei(Cor.Brancas), 0, 4);
        this.addPeca(new Bispo(Cor.Brancas), 0, 5);
        this.addPeca(new Cavalo(Cor.Brancas), 0, 6);
        this.addPeca(new Torre(Cor.Brancas), 0, 7);
        
        this.addPeca(new Torre(Cor.Pretas), 7, 0);
        this.addPeca(new Cavalo(Cor.Pretas), 7, 1);
        this.addPeca(new Bispo(Cor.Pretas), 7, 2);
        this.addPeca(new Dama(Cor.Pretas), 7, 3);
        this.addPeca(new Rei(Cor.Pretas), 7, 4);
        this.addPeca(new Bispo(Cor.Pretas), 7,5);
        this.addPeca(new Cavalo(Cor.Pretas), 7, 6);
        this.addPeca(new Torre(Cor.Pretas), 7, 7);

        this.addPeca(new Peao(Cor.Pretas), 6, 0);
        this.addPeca(new Peao(Cor.Pretas), 6, 1);
        this.addPeca(new Peao(Cor.Pretas), 6, 2);
        this.addPeca(new Peao(Cor.Pretas), 6, 3);
        this.addPeca(new Peao(Cor.Pretas), 6, 4);
        this.addPeca(new Peao(Cor.Pretas), 6, 5);
        this.addPeca(new Peao(Cor.Pretas), 6, 6);
        this.addPeca(new Peao(Cor.Pretas), 6, 7);

        this.addPeca(new Peao(Cor.Brancas), 1, 0);
        this.addPeca(new Peao(Cor.Brancas), 1, 1);
        this.addPeca(new Peao(Cor.Brancas), 1, 2);
        this.addPeca(new Peao(Cor.Brancas), 1, 3);
        this.addPeca(new Peao(Cor.Brancas), 1, 4);
        this.addPeca(new Peao(Cor.Brancas), 1, 5);
        this.addPeca(new Peao(Cor.Brancas), 1, 6);
        this.addPeca(new Peao(Cor.Brancas), 1, 7);
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
