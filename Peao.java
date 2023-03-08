public class Peao extends Peca {

    public Peao(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false);
    }

    public int numMovimentoEnPassant = -1;
    public Casa peaoCapturadoEnPassant;

    public boolean movimentoValido(Casa partida, Casa destino) {
        if (!dentroDoTabuleiro(destino))
            return false;

        int sinal = (this.getJogador() == Cor.Brancas) ? 1 : -1;

        boolean movimentoDeCaptura = (partida.coluna + 1 == destino.coluna || partida.coluna - 1 == destino.coluna)
                && partida.linha + sinal == destino.linha
                && this.getTabuleiro().getPeca(partida.linha + sinal, destino.coluna) != null;

        boolean movimentoNormal = partida.coluna == destino.coluna
                && (partida.linha + sinal == destino.linha
                        || ((partida.linha + (2 * sinal) == destino.linha) && this.numMovimentos == 0))
                        && this.getTabuleiro().getPeca(destino) == null;

        boolean enPassant = this.numMovimentoEnPassant == this.getTabuleiro().contadorMovimentos
                && partida.linha + sinal == destino.linha
                && this.peaoCapturadoEnPassant.coluna == destino.coluna;

        if (enPassant) {
            this.tabuleiro.pecas[this.peaoCapturadoEnPassant.linha][this.peaoCapturadoEnPassant.coluna] = null;
        }

        if (this.numMovimentos == 0 && Math.abs(partida.linha - destino.linha) == 2) {
            Peca peaoDireita = null, peaoEsquerda = null;

            if (destino.coluna + 1 < 8) {
                peaoDireita = this.getTabuleiro().getPeca(destino.linha, destino.coluna + 1);
            }
            if (destino.coluna - 1 >= 0) {
                peaoEsquerda = this.getTabuleiro().getPeca(destino.linha, destino.coluna - 1);
            }

            if (peaoDireita != null && peaoDireita.getClassName() == "Peao") {
                ((Peao) peaoDireita).numMovimentoEnPassant = this.getTabuleiro().contadorMovimentos + 1;
                ((Peao) peaoDireita).peaoCapturadoEnPassant = new Casa(destino.linha, destino.coluna);
            }

            if (peaoEsquerda != null && peaoEsquerda.getClassName() == "Peao") {
                ((Peao) peaoEsquerda).numMovimentoEnPassant = this.getTabuleiro().contadorMovimentos + 1;
                ((Peao) peaoEsquerda).peaoCapturadoEnPassant = new Casa(destino.linha, destino.coluna);
            }
        }

        return movimentoDeCaptura || movimentoNormal || enPassant;
    }

    public String simboloPeca() {
        return "P";
    }

}
