package View;


import javax.swing.*;
import java.awt.*;
import Model.Cor;

public class BotaoCasa extends JButton {

    private Cor corCasa;
    private boolean marcacaoMovimentoPossivel;

    public BotaoCasa(Cor corCasa) {
        this.corCasa = corCasa;
        this.marcacaoMovimentoPossivel = false;

        this.resetBackgroundCasa();

    }

    private void resetBackgroundCasa() {
        setFocusPainted(false);
        if (this.corCasa == Cor.Brancas) {
            setBackground(Color.WHITE);
        }
        else{
            setBackground(Color.decode("#759655"));
        }
    }

    public void marcarMovimentoPossivel() {
        this.marcacaoMovimentoPossivel = true;

        setBackground(Color.decode("#87b5ff"));
        setBorderPainted(true);
    }

    public void removerMarcacaoMovimentoPossivel() {
        if (this.marcacaoMovimentoPossivel) {
            this.resetBackgroundCasa();
            marcacaoMovimentoPossivel = false;
        }
    }
}