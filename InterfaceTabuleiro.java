import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceTabuleiro extends JFrame implements ActionListener {
    private final JButton[] botoes;
    private final JButton[][] botoesMatriz;
    private static final String[] nomes = { "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito" };
    private final Container container;
    private final GridLayout gridLayout1;

    public InterfaceTabuleiro() {
        super("Demonstração GridLayout");
        gridLayout1 = new GridLayout(8, 8); // 2 por 3; gaps de 5
        container = getContentPane();
        setLayout(gridLayout1);
        botoes = new JButton[64];

        botoesMatriz = new JButton[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                botoesMatriz[i][j] = new JButton();
                botoesMatriz[i][j].addActionListener(this);
                botoesMatriz[i][j].setBorderPainted(true);
                botoesMatriz[i][j].setFocusPainted(false);

                if (i % 2 == 0) {
                    if (j % 2 == 0)
                        botoesMatriz[i][j].setBackground(Color.WHITE);
                    else
                        botoesMatriz[i][j].setBackground(Color.BLACK);
                } else {
                    if (j % 2 == 0)
                        botoesMatriz[i][j].setBackground(Color.BLACK);
                    else
                        botoesMatriz[i][j].setBackground(Color.WHITE);
                }

                add(botoesMatriz[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        container.validate(); // rearranja os elementos do container
    }
}
