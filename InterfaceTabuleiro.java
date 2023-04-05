import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceTabuleiro extends JFrame implements ActionListener {
    private final JButton[] botoes;
    private static final String[] nomes = { "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito" };
    private final Container container;
    private final GridLayout gridLayout1;

    public InterfaceTabuleiro() {
        super("Demonstração GridLayout");
        gridLayout1 = new GridLayout(8, 8); // 2 por 3; gaps de 5
        container = getContentPane();
        setLayout(gridLayout1);
        botoes = new JButton[64];

        for (int i = 0; i < 64; i++) {
            botoes[i] = new JButton();
            botoes[i].addActionListener(this);

            botoes[i].setBorderPainted(true);
            botoes[i].setFocusPainted(false);

            if(0 <= i%16 && i%16 < 8){
              if (i % 2 == 0)
                  botoes[i].setBackground(Color.WHITE);
              else
                  botoes[i].setBackground(Color.BLACK);
            }
            else{
              if (i % 2 == 1)
                  botoes[i].setBackground(Color.WHITE);
              else
                  botoes[i].setBackground(Color.BLACK);
            }

            add(botoes[i]);

        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        container.validate(); // rearranja os elementos do container
    }
}
