package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.Tabuleiro;
import Model.Peca;
import java.util.List;
import Model.Casa;


public class InterfaceTabuleiro extends JFrame implements ActionListener {
    private final JButton[][] botoesMatriz;
    private final Container container;

    private final Tabuleiro tabuleiro;

    private boolean casaClicada = false;

    private int linhaClicada, colunaClicada;

    public void resetaCores(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i%2 == j%2)
                    botoesMatriz[i][j].setBackground(Color.WHITE);
                else
                    botoesMatriz[i][j].setBackground(Color.decode("#759655"));
            }
        }
    }

    public InterfaceTabuleiro(Tabuleiro tabuleiro){
        super("vem de xadra vem");

        this.tabuleiro = tabuleiro;


        GridLayout gridLayout1 = new GridLayout(8, 8);
        container = getContentPane();
        setLayout(gridLayout1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 480);
        setVisible(true);
        botoesMatriz = new JButton[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int linha = i;
                final int coluna = j;
                botoesMatriz[i][j] = new JButton();
                botoesMatriz[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        casaClicada = true;
                        linhaClicada = linha;
                        colunaClicada = coluna;
                    }
                });
                botoesMatriz[i][j].setBorderPainted(false);
                botoesMatriz[i][j].setFocusPainted(false);

                add(botoesMatriz[i][j]);
            }

        }
        resetaCores();

    }


    public Casa getCasaClicada() throws InterruptedException {
        while(!casaClicada){
            Thread.sleep(1);
        }

        casaClicada = false;
        return new Casa(linhaClicada, colunaClicada);
    }

    public void pintaMovimentosPossiveis(List<Casa> movimentosPossives){
        for(Casa casa : movimentosPossives){
            botoesMatriz[casa.linha][casa.coluna].setBackground(Color.decode("#87b5ff"));
        }
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        container.validate(); // rearranja os elementos do container
    }

    public String caminhoImagemPeca(Peca p){
        if(p == null) return null;
        switch(p.getCor()) {
            case Brancas:
                switch (p.getSimbolo()) {
                    case "P" -> {
                        return "./src/Images/Chess_plt60.png";
                    }
                    case "C" -> {
                        return "./src/Images/Chess_nlt60.png";
                    }
                    case "R" -> {
                        return "./src/Images/Chess_klt60.png";
                    }
                    case "T" -> {
                        return "./src/Images/Chess_rlt60.png";
                    }
                    case "D" -> {
                        return "./src/Images/Chess_qlt60.png";
                    }
                    case "B" -> {
                        return "./src/Images/Chess_blt60.png";
                    }
                }
            case Pretas:
                switch (p.getSimbolo()) {
                    case "P" -> {
                        return "./src/Images/Chess_pdt60.png";
                    }
                    case "C" -> {
                        return "./src/Images/Chess_ndt60.png";
                    }
                    case "R" -> {
                        return "./src/Images/Chess_kdt60.png";
                    }
                    case "T" -> {
                        return "./src/Images/Chess_rdt60.png";
                    }
                    case "D" -> {
                        return "./src/Images/Chess_qdt60.png";
                    }
                    case "B" -> {
                        return "./src/Images/Chess_bdt60.png";
                    }
                }


        }
        return null;
    }
    public void updateTabuleiro()
    {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                String caminhoImagem = caminhoImagemPeca(tabuleiro.getPeca(i, j));

                botoesMatriz[i][j].setIcon(new ImageIcon(caminhoImagem));

            }
        }
    }
}
