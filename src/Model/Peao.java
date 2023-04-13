package Model;

public class Peao extends Peca {

    public Peao(Cor jogador, Tabuleiro tabuleiro) {
        super(jogador, tabuleiro, false, "P");
    }


    public boolean verificaEnPassant(Casa partida, Casa destino){
        int sinal = (this.getJogador() == Cor.Brancas) ? -1 : 1;

        Tabuleiro tabuleiro = this.getTabuleiro();

        boolean posicaoCorreta = partida.linha + sinal == destino.linha &&
                Math.abs(destino.coluna - partida.coluna) == 1;
        boolean temPecaAoLado = tabuleiro.getPecas()[partida.linha][destino.coluna] != null;
        Peca pecaAoLado = tabuleiro.getPecas()[partida.linha][destino.coluna];
        boolean temPeaoAoLado = temPecaAoLado && pecaAoLado instanceof Peao;

        return  posicaoCorreta && temPeaoAoLado
                && pecaAoLado.numUltimoMovimentoRealizado == tabuleiro.contadorMovimentos-1
                && pecaAoLado.numMovimentos == 1;

    }

    public static Casa enPassant(Casa partida, Casa destino){
        return new Casa(partida.linha, destino.coluna);
    }

    public boolean movimentoValido(Casa partida, Casa destino) {
        int sinal = (this.getJogador() == Cor.Brancas) ? -1 : 1;

        boolean movimentoDeCaptura = (partida.coluna + 1 == destino.coluna || partida.coluna - 1 == destino.coluna)
                && partida.linha + sinal == destino.linha
                && this.getTabuleiro().getPeca(partida.linha + sinal, destino.coluna) != null;

        boolean movimentoNormal = partida.coluna == destino.coluna
                && (partida.linha + sinal == destino.linha
                        || ((partida.linha + (2 * sinal) == destino.linha) && this.numMovimentos == 0))
                        && this.getTabuleiro().getPeca(destino) == null;

        boolean enPassant = verificaEnPassant(partida, destino);

        return movimentoDeCaptura || movimentoNormal || enPassant;
    }
}
