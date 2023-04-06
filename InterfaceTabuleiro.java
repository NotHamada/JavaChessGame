import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceTabuleiro extends JFrame implements ActionListener {
    private final JButton[][] botoesMatriz;
    private final Container container;
    private final GridLayout gridLayout1;

    public InterfaceTabuleiro() {
        super("Xadrez pai vamo jogar uma bullet aí: Hamada_Chess (chess.com) ou Matheus_Hamada (lichess.org)");
        gridLayout1 = new GridLayout(8, 8); // 2 por 3; gaps de 5
        container = getContentPane();
        setLayout(gridLayout1);
        botoesMatriz = new JButton[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                botoesMatriz[i][j] = new JButton();
                botoesMatriz[i][j].addActionListener(this);
                botoesMatriz[i][j].setBorderPainted(false);
                botoesMatriz[i][j].setFocusPainted(false);

                if(i%2 == j%2) 
                    botoesMatriz[i][j].setBackground(Color.WHITE);
                
                else 
                    botoesMatriz[i][j].setBackground(Color.decode("#759655"));


                add(botoesMatriz[i][j]);
            }
        }

        ColocarPecas();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        container.validate(); // rearranja os elementos do container
    }

    public void ColocarPecas()
    {
        botoesMatriz[0][0].setIcon(new ImageIcon("./Images/Chess_rdt60.png"));
        botoesMatriz[0][1].setIcon(new ImageIcon("./Images/Chess_ndt60.png"));
        botoesMatriz[0][2].setIcon(new ImageIcon("./Images/Chess_bdt60.png"));
        botoesMatriz[0][3].setIcon(new ImageIcon("./Images/Chess_qdt60.png"));
        botoesMatriz[0][4].setIcon(new ImageIcon("./Images/Chess_kdt60.png"));
        botoesMatriz[0][5].setIcon(new ImageIcon("./Images/Chess_bdt60.png"));
        botoesMatriz[0][6].setIcon(new ImageIcon("./Images/Chess_ndt60.png"));
        botoesMatriz[0][7].setIcon(new ImageIcon("./Images/Chess_rdt60.png"));
        
        botoesMatriz[1][0].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        botoesMatriz[1][1].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        botoesMatriz[1][2].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        botoesMatriz[1][3].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        botoesMatriz[1][4].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        botoesMatriz[1][5].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        botoesMatriz[1][6].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        botoesMatriz[1][7].setIcon(new ImageIcon("./Images/Chess_pdt60.png"));
        
        botoesMatriz[6][0].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        botoesMatriz[6][1].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        botoesMatriz[6][2].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        botoesMatriz[6][3].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        botoesMatriz[6][4].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        botoesMatriz[6][5].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        botoesMatriz[6][6].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        botoesMatriz[6][7].setIcon(new ImageIcon("./Images/Chess_plt60.png"));
        
        botoesMatriz[7][0].setIcon(new ImageIcon("./Images/Chess_rlt60.png"));
        botoesMatriz[7][1].setIcon(new ImageIcon("./Images/Chess_nlt60.png"));
        botoesMatriz[7][2].setIcon(new ImageIcon("./Images/Chess_blt60.png"));
        botoesMatriz[7][3].setIcon(new ImageIcon("./Images/Chess_qlt60.png"));
        botoesMatriz[7][4].setIcon(new ImageIcon("./Images/Chess_klt60.png"));
        botoesMatriz[7][5].setIcon(new ImageIcon("./Images/Chess_blt60.png"));
        botoesMatriz[7][6].setIcon(new ImageIcon("./Images/Chess_nlt60.png"));
        botoesMatriz[7][7].setIcon(new ImageIcon("./Images/Chess_rlt60.png"));
    }
}
