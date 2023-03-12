import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {

    public Rei(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        if (!dentroDoTabuleiro(destino))
            return false;

        ignoraColisao = false;

        boolean movimentoNormal = (Math.abs(partida.linha - destino.linha) == 1
                || Math.abs(partida.linha - destino.linha) == 0)
                && (Math.abs(partida.coluna - destino.coluna) == 1
                        || Math.abs(partida.coluna - destino.coluna) == 0);
        boolean roquePermitido = false;

        Tabuleiro tabuleiro = this.getTabuleiro();

        String destinoTorre = "";
        String partidaTorre = "";

        if (partida.equals(tabuleiro.strToCasa("e8")) && destino.equals(tabuleiro.strToCasa("g8"))) {
            Peca rei = tabuleiro.getPeca(partida);
            Peca torre = tabuleiro.getPeca(tabuleiro.strToCasa("h8"));

            if (rei.numMovimentos == 0 && torre != null && torre.getClassName() == "Torre"
                    && torre.numMovimentos == 0) {

                roquePermitido = tabuleiro.getPeca("f8") == null
                        && tabuleiro.getPeca("g8") == null
                        && !tabuleiro.estaAmeacado("f8", rei.jogador)
                        && !tabuleiro.estaAmeacado("g8", rei.jogador);
            }

            if (roquePermitido) {
                partidaTorre = "h8";
                destinoTorre = "f8";
            }

        } else if (partida.equals(tabuleiro.strToCasa("e8")) && destino.equals(tabuleiro.strToCasa("c8"))) {
            Peca rei = tabuleiro.getPeca(partida);
            Peca torre = tabuleiro.getPeca(tabuleiro.strToCasa("a8"));

            if (rei.numMovimentos == 0 && torre != null && torre.getClassName() == "Torre"
                    && torre.numMovimentos == 0) {

                roquePermitido = tabuleiro.getPeca("d8") == null
                        && tabuleiro.getPeca("c8") == null
                        && tabuleiro.getPeca("b8") == null
                        && !tabuleiro.estaAmeacado("d8", rei.jogador)
                        && !tabuleiro.estaAmeacado("c8", rei.jogador)
                        && !tabuleiro.estaAmeacado("b8", rei.jogador);
            }

            if (roquePermitido) {
                partidaTorre = "a8";
                destinoTorre = "d8";
            }
        }

        else if (partida.equals(tabuleiro.strToCasa("e1")) && destino.equals(tabuleiro.strToCasa("c1"))) {
            Peca rei = tabuleiro.getPeca(partida);
            Peca torre = tabuleiro.getPeca(tabuleiro.strToCasa("a1"));

            if (rei.numMovimentos == 0 && torre != null && torre.getClassName() == "Torre"
                    && torre.numMovimentos == 0) {

                /*
                 * System.out.printf("%b %b %b %b %b %b",
                 * (tabuleiro.getPeca("d1") == null),
                 * (tabuleiro.getPeca("c1") == null),
                 * tabuleiro.getPeca("b1") == null,
                 * !tabuleiro.estaAmeacado("d1"),
                 * !tabuleiro.estaAmeacado("c1"),
                 * !tabuleiro.estaAmeacado("b1"));
                 */

                roquePermitido = tabuleiro.getPeca("d1") == null
                        && tabuleiro.getPeca("c1") == null
                        && tabuleiro.getPeca("b1") == null
                        && !tabuleiro.estaAmeacado("d1", rei.jogador)
                        && !tabuleiro.estaAmeacado("c1", rei.jogador)
                        && !tabuleiro.estaAmeacado("b1", rei.jogador);
            }

            if (roquePermitido) {
                partidaTorre = "a1";
                destinoTorre = "d1";
            }
        }

        else if (partida.equals(tabuleiro.strToCasa("e1")) && destino.equals(tabuleiro.strToCasa("g1"))) {
            Peca rei = tabuleiro.getPeca(partida);
            Peca torre = tabuleiro.getPeca(tabuleiro.strToCasa("h1"));

            if (rei.numMovimentos == 0 && torre != null && torre.getClassName() == "Torre"
                    && torre.numMovimentos == 0) {

                roquePermitido = tabuleiro.getPeca("f1") == null
                        && tabuleiro.getPeca("g1") == null
                        && !tabuleiro.estaAmeacado("f1", rei.jogador)
                        && !tabuleiro.estaAmeacado("g1", rei.jogador);
            }
            if (roquePermitido) {
                destinoTorre = "f1";
                partidaTorre = "h1";
            }
        }

        if (roquePermitido) {
            try {
                roque(partidaTorre, destinoTorre);
            } catch (MovementNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }

        ignoraColisao = true;

        return movimentoNormal || roquePermitido;

    }

    public void roque(String partida, String destino) throws MovementNotAllowedException {
        var torre = this.tabuleiro.getPeca(partida);
        Casa partidaTorre = this.tabuleiro.strToCasa(partida);
        Casa destinoTorre = this.tabuleiro.strToCasa(destino);

        this.tabuleiro.pecas[partidaTorre.linha][partidaTorre.coluna] = null;
        this.tabuleiro.pecas[destinoTorre.linha][destinoTorre.coluna] = torre;

        torre.numMovimentos++;

        this.tabuleiro.contadorMovimentos++;
    }

    public String simboloPeca() {
        return "R";
    }
}
