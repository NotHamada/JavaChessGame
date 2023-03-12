import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {

    public Rei(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false, "R");
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        return verificaMovimentoNormal(partida, destino) || verificaRoque(partida, destino);
    }

    private boolean verificaMovimentoNormal(Casa partida, Casa destino) {
        return (Math.abs(partida.linha - destino.linha) == 1
                || Math.abs(partida.linha - destino.linha) == 0)
                && (Math.abs(partida.coluna - destino.coluna) == 1
                        || Math.abs(partida.coluna - destino.coluna) == 0);
    }

    private boolean verificaRoque(Casa partida, Casa destino) {
        Tabuleiro tabuleiro = this.getTabuleiro();
        Peca torre;

        String partidaEDestino = tabuleiro.casaToStr(partida) + tabuleiro.casaToStr(destino);

        // Roque curto das brancas
        if ("e1g1".equals(partidaEDestino)) {
            torre = tabuleiro.getPeca(tabuleiro.strToCasa("h1"));

            if (!verificaCondicaoRoque(partida, destino, torre))
                return false;

            roque("h1", "f1");
        }
        // Roque grande das brancas
        else if ("e1c1".equals(partidaEDestino)) {
            torre = tabuleiro.getPeca(tabuleiro.strToCasa("a1"));

            if (!verificaCondicaoRoque(partida, destino, torre))
                return false;

            roque("a1", "d1");
        }
        // Roque curto das pretas
        else if ("e8g8".equals(partidaEDestino)) {
            torre = tabuleiro.getPeca(tabuleiro.strToCasa("h8"));

            if (!verificaCondicaoRoque(partida, destino, torre))
                return false;

            roque("h8", "f8");
        }
        // Roque grande das pretas
        else if ("e8c8".equals(partidaEDestino)) {
            torre = tabuleiro.getPeca(tabuleiro.strToCasa("a8"));

            if (!verificaCondicaoRoque(partida, destino, torre))
                return false;

            roque("a8", "d8");
        }
        // Não é roque
        else {
            return false;
        }

        return true;
    }

    private boolean verificaCondicaoRoque(Casa partida, Casa destino, Peca torre) {
        Peca rei = tabuleiro.getPeca(partida);

        if (rei.numMovimentos > 0 || torre.numMovimentos > 0 || !(torre instanceof Torre))
            return false;

        int minColuna = Math.min(partida.coluna, destino.coluna);
        int maxColuna = Math.max(partida.coluna, destino.coluna);

        for (int i = minColuna; i <= maxColuna; i++) {
            Casa casaAChecar = new Casa(partida.linha, i);
            if (tabuleiro.estaAmeacado(casaAChecar, rei.jogador))
                return false;
        }
        return true;
    }

    private void roque(String partida, String destino) {
        var torre = this.tabuleiro.getPeca(partida);
        Casa partidaTorre = this.tabuleiro.strToCasa(partida);
        Casa destinoTorre = this.tabuleiro.strToCasa(destino);

        this.tabuleiro.pecas[partidaTorre.linha][partidaTorre.coluna] = null;
        this.tabuleiro.pecas[destinoTorre.linha][destinoTorre.coluna] = torre;

        torre.numMovimentos++;

        this.tabuleiro.contadorMovimentos++;
    }

    public List<Casa> casasDoRei(Casa casaInicial) {
        List<Casa> casas = new ArrayList<Casa>();

        if (casaInicial.linha + 1 <= 7) {
            var superior = new Casa(casaInicial.linha + 1, casaInicial.coluna);
            casas.add(superior);

            if (casaInicial.coluna - 1 >= 0) {
                var superiorEsquerda = new Casa(casaInicial.linha + 1, casaInicial.coluna - 1);
                casas.add(superiorEsquerda);
                var esquerda = new Casa(casaInicial.linha, casaInicial.coluna - 1);
                casas.add(esquerda);
            }

            if (casaInicial.coluna + 1 <= 7) {
                var superiorDireita = new Casa(casaInicial.linha + 1, casaInicial.coluna + 1);
                casas.add(superiorDireita);
                var direita = new Casa(casaInicial.linha, casaInicial.coluna + 1);
                casas.add(direita);
            }

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
}
