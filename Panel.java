import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private JButton numbers[] = new JButton[10];    // количество кнопок (по умолчания было 10 = от 0 до 9)
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JTextField output = new JTextField();

    private JButton mc = new JButton("MC");
    private JButton mMS = new JButton("Ms");
    private JButton mMPus = new JButton("M+");
    private JButton mMr = new JButton("Mr");

    private JButton c = new JButton("C");
    private JButton division = new JButton("/");
    private JButton multiplier = new JButton("Х");
    private JButton delete = new JButton("<-");

    private JButton percent = new JButton("%");
    private JButton dot = new JButton(".");
    private JButton eqls = new JButton("=");

    private JButton minus = new JButton("-");
    private JButton plus = new JButton("+");
    private JButton square = new JButton("√");

    double firstValue = 0;
    String operation = "+";
    double memory = 0; // память для мс, мр и прочих м кнопок


    public Panel() {
        setLayout(null);
        setBackground(Color.black);

        setFocusable(true);
        grabFocus();

        square.setBounds(190, 310, 50, 50);
        square.setFont(font);
        add(square);

        plus.setBounds(190, 190, 50, 50);
        plus.setFont(font);
        add(plus);

        minus.setBounds(190, 250, 50, 50);
        minus.setFont(font);
        add(minus);

        dot.setBounds(130, 370, 50, 50);
        dot.setFont(font);
        add(dot);

        eqls.setBounds(190, 370, 50, 50);
        eqls.setFont(font);
        add(eqls);

        percent.setBounds(10, 370, 50, 50);
        percent.setFont(font);
        add(percent);

        c.setBounds(10, 130, 50, 50);
        c.setFont(font);
        c.setForeground(Color.RED);
        add(c);

        division.setBounds(70, 130, 50, 50);
        division.setFont(font);
        add(division);

        multiplier.setBounds(130, 130, 50, 50);
        multiplier.setFont(font);
        add(multiplier);

        delete.setBounds(190, 130, 50, 50);
        delete.setFont(font);
        add(delete);

        mMr.setBounds(190, 70, 50, 50);
        mMr.setFont(font);
        add(mMr);

        mMPus.setBounds(130, 70, 50, 50);
        mMPus.setFont(font);
        add(mMPus);

        mMS.setBounds(70, 70, 50, 50);
        mMS.setFont(font);
        add(mMS);

        mc.setBounds(10, 70, 50, 50);
        mc.setFont(font);
        add(mc);

        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 370, 50, 50);
        numbers[0].setFont(font);
        add(numbers[0]);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                numbers[x * 3 + y + 1] = new JButton((y * 3 + x + 1) + "");
                numbers[x * 3 + y + 1].setBounds(x * (50 + 10) + 10, y * (50 + 10) + 190, 50, 50);
                numbers[x * 3 + y + 1].setFont(font);
                add(numbers[x * 3 + y + 1]);
            }
        }

        output.setBounds(10, 10, 230, 50);
        output.setEditable(false);
        output.setFont(font);
        add(output);

        ActionListener l = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            output.setText(output.getText() + b.getText());
        };

        // цикл форич позволяет пробежаться по объектам ( в данном случае по кнопкам)
        for (JButton b : numbers) {
            b.addActionListener(l);
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                char symbol = e.getKeyChar();
                if(!Character.isDigit(symbol)){
                    return;
                }
                output.setText(output.getText() + symbol);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = output.getText();
                output.setText(temp.substring(0,temp.length()-1));
            }
        });
        // реализован запрет на повтороение символа
        dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = output.getText();
                if (temp.contains(".")) {
                    output.setText(output.getText() + "");
                } else {
                    output.setText(output.getText() + ".");
                }
            }
        });

        mc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory = 0;
            }
        });

        mMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory = Double.valueOf(output.getText());
                output.setText("");
            }
        });

        mMPus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory = Double.valueOf(output.getText()) + memory;
                output.setText("");
            }
        });



        mMr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText(memory + "");
            }
        });

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
                setFocusable(true);
                grabFocus();
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "+";
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "-";
            }
        });

        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "/";
            }
        });

        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText(Math.sqrt(firstValue) + "");
            }
        });

        multiplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "*";
            }
        });

        percent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "%";
            }
        });


        eqls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double secondValue = Double.valueOf(output.getText());
                if("+".equals(operation)){
                    output.setText((firstValue+secondValue)+"");
                }
                if("-".equals(operation)){
                    output.setText((firstValue-secondValue)+"");
                }
                if("*".equals(operation)){
                    output.setText((firstValue*secondValue)+"");
                }
                if("/".equals(operation)){
                    output.setText((firstValue/secondValue)+"");
                }
                if("%".equals(operation)) {
                    output.setText(((firstValue / 100) * secondValue) + "");
                }

                firstValue = 0;
                operation = "+";
            }
        });
    }
}



