package swing;

import javax.swing.*;

import logic.Field;

public class Window extends JFrame {
    public static JFrame gameFrame, getSizeFrame;
    JButton insertBtn;
    JTextField txtWidth, txtHeight, txtMines;
    JLabel lblWidth, lblHeight, lblMines;
    Field mineField;

    public void gameStart() {
        callWindows();
    }

    public void callWindows() {
        getSizeFrame = new JFrame("Width and height of the field");
        getSizeFrame.setSize(450, 400);
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
        insertBtn.addActionListener(e -> {
           try {
             mineField = new Field(Integer.parseInt(txtWidth.getText()), Integer.parseInt(txtHeight.getText()), Integer.parseInt(txtMines.getText()));

           } catch (Exception e1) {
               JOptionPane.showMessageDialog(getSizeFrame, "Insert correct values!");
           }
            if (mineField.getWidth() != 0 && mineField.getHeight() != 0) {
                getSizeFrame.setVisible(false);
                gameFrame = new JFrame();
                gameFrame.setSize(500,500);
                gameFrame.setLayout(null);
                gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                for (int i = 0 ; i < mineField.getWidth(); i++){
                    for (int j = 0; j < mineField.getHeight(); j++){
                        gameFrame.add(mineField.field[i][j]);
                    }
                }
                gameFrame.repaint();
                gameFrame.setTitle("Minesweeper");
                gameFrame.setVisible(true);
            }
        });
    }
}

