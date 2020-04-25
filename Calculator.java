import javax.swing.*;
import java.awt.*;

public class Calculator {

    private JFrame window;

    public Calculator(){
        window = new JFrame("Calculator");
        window.setSize(250, 450);
        window.add(new Panel());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });

    }



}
