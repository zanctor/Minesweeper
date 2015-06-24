package swing;

import javax.swing.*;
import java.awt.*;

import logic.Field;



public class Window extends JFrame {
    public static JFrame gameFrame, getSizeFrame;
    JButton insertBtn, restartBtn;
    JTextField txtWidth, txtHeight, txtMines;
    JLabel lblWidth, lblHeight, lblMines;
    public static JLabel lblFlags;
    Field mineField;
    ImageIcon icon = new ImageIcon("icon.png");
    Image im = Toolkit.getDefaultToolkit().getImage("icon.png");

    public void gameStart() {
        callWindows();
    }

    public void callWindows() {
        callGetSize();
        callGameFrame();
    }

    public void callGetSize() {
        getSizeFrame = new JFrame("Width and height of the mine field");
        getSizeFrame.setIconImage(im);
        getSizeFrame.setSize(450, 450);
        getSizeFrame.setLayout(null);
        getSizeFrame.setVisible(true);
        getSizeFrame.add(insertBtn = new JButton("Insert values"));
        getSizeFrame.add(lblWidth = new JLabel("Input number of cells of width: "));
        getSizeFrame.add(txtWidth = new JTextField());
        getSizeFrame.add(lblHeight = new JLabel("Input number of cells of height: "));
        getSizeFrame.add(txtHeight = new JTextField());
        getSizeFrame.add(lblMines = new JLabel("Input number of mines: "));
        getSizeFrame.add(txtMines = new JTextField());
        getSizeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        insertBtn.setBounds(200, 350, 150, 30);
        lblWidth.setBounds(50, 50, 250, 30);
        txtWidth.setBounds(50, 100, 50, 30);
        lblHeight.setBounds(50, 150, 250, 30);
        txtHeight.setBounds(50, 200, 50, 30);
        lblMines.setBounds(50, 250, 250, 30);
        txtMines.setBounds(50, 300, 50, 30);
    }

    public void callGameFrame() {
        insertBtn.addActionListener(e -> {
            try {
                mineField = new Field(Integer.parseInt(txtWidth.getText()), Integer.parseInt(txtHeight.getText()), Integer.parseInt(txtMines.getText()));

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(getSizeFrame, "Insert correct values!");
                e1.printStackTrace();
            }
            if (Field.getWidth() != 0 && Field.getHeight() != 0) {
                getSizeFrame.setVisible(false);
                gameFrame = new JFrame("Minesweeper");
                gameFrame.setIconImage(im);
                gameFrame.add(lblFlags = new JLabel("00"));
                restartBtn = new JButton(icon);
                gameFrame.add(restartBtn);
                restartBtn.setBounds(600, 500, 64, 64);
                restartBtn.addActionListener(e2 -> {
                    removingField();
                    mineField = new Field(Field.getWidth(), Field.getHeight(), Field.getMinesNumber());
                    imaginatingField();
                    lblFlags.setText(Integer.toString(mineField.getRemainingFlags()));
                    gameFrame.repaint();
                });
                lblFlags.setText(Integer.toString(mineField.getRemainingFlags()));
                lblFlags.setEnabled(false);
                lblFlags.setBounds(600, 600, 100, 50);
                gameFrame.setSize(70 * Field.getWidth(), 70 * Field.getHeight());
                gameFrame.setLayout(null);
                gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                imaginatingField();
                gameFrame.repaint();
                gameFrame.setVisible(true);
            }
        });
    }

    public void imaginatingField(){
        for (int i = 0; i < Field.getHeight(); i++) {
            for (int j = 0; j < Field.getWidth(); j++) {
                gameFrame.add(mineField.field[i][j]);
            }
        }
    }
    public void removingField(){
        for (int i = 0; i < Field.getHeight(); i++) {
            for (int j = 0; j < Field.getWidth(); j++) {
                gameFrame.remove(mineField.field[i][j]);
            }
        }
    }
}

