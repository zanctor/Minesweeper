package swing;

import javax.swing.*;

import logic.Field;

public class Window extends JFrame {
    JFrame gameFrame;
    JFrame getSizeFrame;
    JButton insertBtn;
    JTextField txtWidth, txtHeight;
    JLabel lblWidth, lblHeight;

    public void gameStart() {
        setWindow();
        callGame();
    }

    public void setWindow() {
        getSizeFrame = new JFrame("Width and height of the field");
        gameFrame.setSize(500, 500);
        getSizeFrame.setLayout(null);
        getSizeFrame.setVisible(true);
        getSizeFrame.setSize(300, 300);
        getSizeFrame.add(insertBtn = new JButton("Insert values"));
        getSizeFrame.add(lblWidth = new JLabel("Input number of cells of width: "));
        getSizeFrame.add(txtWidth = new JTextField("0"));
        getSizeFrame.add(lblHeight = new JLabel("Input number of cells of height: "));
        getSizeFrame.add(txtHeight = new JTextField("0"));
        insertBtn.setBounds(200, 200, 50, 20);
        lblWidth.setBounds(200, 200, 50, 20);
        txtWidth.setBounds(200, 200, 50, 20);
        lblHeight.setBounds(200, 200, 50, 20);
        txtHeight.setBounds(200, 200, 50, 20);
        insertBtn.addActionListener(e -> {
            if (Integer.parseInt(txtWidth.getText()) != 0 && Integer.parseInt(txtHeight.getText()) != 0) {
                new Field(Integer.parseInt(txtWidth.getText()), Integer.parseInt(txtHeight.getText()));
            }
        });}

    public void callGame() {
        if (Field.getWidth() != 0 && Field.getHeight() != 0) {
            getSizeFrame.setVisible(false);
            gameFrame = new JFrame();
            gameFrame.add(Field.panel);
            gameFrame.setTitle("Minesweeper");
            gameFrame.setVisible(true);
        }
    }

}

