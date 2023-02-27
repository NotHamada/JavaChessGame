import java.util.ArrayList;
import java.util.Arrays;

public class Tabuleiro {

    public static final int maxLinhas = 8;
    public static final int maxColunas = 8;

    private ArrayList<Peca> pecas;

    public Tabuleiro() {
        this.pecas = new ArrayList<>();
    }

    public void addPeca(Peca peca) {

        if (peca.getLinha() < 0
                || peca.getLinha() > maxLinhas
                || peca.getColuna() < 0
                || peca.getColuna() > maxColunas
        ) {
            System.out.println("Os valores de linha e coluna devem estar no intervalo [0," + (maxColunas - 1) + "]");
            return;
        }

        for (Peca p : pecas) {
            if (p.getColuna() == peca.getColuna()
                    && p.getLinha() == peca.getLinha()
            ) {
                System.out.println("Ja existe peca nesta posicao");
                return;
            }
        }

        pecas.add(peca);
    }

    public void inicializaPosicao() {
        pecas.clear();

        pecas.add(new Torre(0, 0, Cor.Brancas));
        pecas.add(new Cavalo(0, 1, Cor.Brancas));
        pecas.add(new Bispo(0, 2, Cor.Brancas));
        pecas.add(new Dama(0, 3, Cor.Brancas));
        pecas.add(new Rei(0, 4, Cor.Brancas));
        pecas.add(new Bispo(0, 5, Cor.Brancas));
        pecas.add(new Cavalo(0, 6, Cor.Brancas));
        pecas.add(new Torre(0, 7, Cor.Brancas));
        
        pecas.add(new Torre(7, 0, Cor.Pretas));
        pecas.add(new Cavalo(7, 1, Cor.Pretas));
        pecas.add(new Bispo(7, 2, Cor.Pretas));
        pecas.add(new Dama(7, 3, Cor.Pretas));
        pecas.add(new Rei(7, 4, Cor.Pretas));
        pecas.add(new Bispo(7, 5, Cor.Pretas));
        pecas.add(new Cavalo(7, 6, Cor.Pretas));
        pecas.add(new Torre(7, 7, Cor.Pretas));

        pecas.add(new Peao(6, 0, Cor.Pretas));
        pecas.add(new Peao(6, 1, Cor.Pretas));
        pecas.add(new Peao(6, 2, Cor.Pretas));
        pecas.add(new Peao(6, 3, Cor.Pretas));
        pecas.add(new Peao(6, 4, Cor.Pretas));
        pecas.add(new Peao(6, 5, Cor.Pretas));
        pecas.add(new Peao(6, 6, Cor.Pretas));
        pecas.add(new Peao(6, 7, Cor.Pretas));

        pecas.add(new Peao(1, 0, Cor.Brancas));
        pecas.add(new Peao(1, 1, Cor.Brancas));
        pecas.add(new Peao(1, 2, Cor.Brancas));
        pecas.add(new Peao(1, 3, Cor.Brancas));
        pecas.add(new Peao(1, 4, Cor.Brancas));
        pecas.add(new Peao(1, 5, Cor.Brancas));
        pecas.add(new Peao(1, 6, Cor.Brancas));
        pecas.add(new Peao(1, 7, Cor.Brancas));
    }

    @Override
    public String toString() {

        String[][] pecasString = new String[maxLinhas][maxColunas];
        Arrays.stream(pecasString).forEach(p -> Arrays.fill(p, " "));

        pecas.forEach(p -> pecasString[p.getLinha()][p.getColuna()] = p.toString());

        String output = "";
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                output += "|" + pecasString[i][j];
            }
            output += "|\n";
        }
        return output;
    }
}
