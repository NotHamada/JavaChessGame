package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Model.*;


import java.util.List;
import java.io.File;
import java.util.regex.Pattern;


public class InterfaceTabuleiro extends JFrame implements ActionListener {
    private final JButton[][] botoesMatriz;
    private final Container container;

    private final Tabuleiro tabuleiro;

    private boolean casaClicada = false;

    private String IMGS_PATH;
    
    private String separator = System.getProperty("file.separator");


    private int linhaClicada, colunaClicada;

    public void resetaCores(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //botoesMatriz[i][j].setBorderPainted(false);
                botoesMatriz[i][j].setFocusPainted(false);
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



        getImgsPath();


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

                add(botoesMatriz[i][j]);
            }

        }
        resetaCores();

    }

    private void getImgsPath() {

        if(separator.equals("\\")){
            separator = "\\";
        }

        File project_path = new File(".");

        String[] project_path_tokens = project_path.getAbsolutePath().split(Pattern.quote(separator));

        int num_folders = project_path_tokens.length;
        switch (project_path_tokens[num_folders - 2]) {
            case "JavaChessGame" -> IMGS_PATH = project_path + separator + "src" + separator;
            case "src" -> IMGS_PATH = project_path + separator + separator;
            default -> IMGS_PATH = project_path + separator + "JavaChessGame" + separator + "src" + separator;
        }
        File images_path = new File(IMGS_PATH);
        if(!images_path.exists()){
            throw new RuntimeException("Não foi possivel encontrar o diretório das imagens. Certifique-se" +
                    "de rodar o projeto a partir do diretório de projeto.");
        }


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
            botoesMatriz[casa.linha][casa.coluna].setBorderPainted(true);
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
                        return IMGS_PATH + "Images" + separator + "Chess_plt60.png";
                    }
                    case "C" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_nlt60.png";
                    }
                    case "R" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_klt60.png";
                    }
                    case "T" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_rlt60.png";
                    }
                    case "D" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_qlt60.png";
                    }
                    case "B" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_blt60.png";
                    }
                }
            case Pretas:
                switch (p.getSimbolo()) {
                    case "P" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_pdt60.png";
                    }
                    case "C" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_ndt60.png";
                    }
                    case "R" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_kdt60.png";
                    }
                    case "T" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_rdt60.png";
                    }
                    case "D" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_qdt60.png";
                    }
                    case "B" -> {
                        return IMGS_PATH + "Images" + separator + "Chess_bdt60.png";
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

    public Peca getPecaPromovida(Cor turno) {
        return new Dama(turno, tabuleiro);
    }
}
