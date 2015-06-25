package swing;

import javax.swing.*;
import java.awt.*;

import logic.Field;



public class Window extends JFrame {
    public static JFrame gameFrame, getSizeFrame;
    JButton insertBtn, restartBtn;
    JPanel minePnl, flagPnl;
    JTextField txtWidth, txtHeight, txtMines;
    JLabel lblWidth, lblHeight, lblMines, lblFlags;
    public static JTextField txtFlags = new JTextField();
    private Field mineField;
    Font font = new Font("Verdana", Font.BOLD, 16);
    ImageIcon icon = new ImageIcon("images/icon.png");
    Image im = Toolkit.getDefaultToolkit().getImage("images/icon.png");

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
                gameFrame.add(txtFlags);
                restartBtn = new JButton(icon);
                flagPnl = new JPanel();
                flagPnl.setLayout(null);
                flagPnl.add(restartBtn);
                restartBtn.setBounds(15, 15, 64, 64);
                restartBtn.addActionListener(e2 -> {
                    operatingField(2);
                    mineField = new Field(Field.getWidth(), Field.getHeight(), Field.getMinesNumber());
                    operatingField(1);
                    gameFrame.repaint();
                });
                txtFlags.setBounds(250, 50, 150, 50);
                txtFlags.setEnabled(false);
                txtFlags.setFont(font);
                flagPnl.setBounds(10, 10, 400, 120);
                gameFrame.add(flagPnl);
                lblFlags = new JLabel("Number of flags: ");
                flagPnl.add(lblFlags);
                lblFlags.setBounds(100, 40, 150, 50);
                gameFrame.setSize(70 * Field.getWidth(), 70 * Field.getHeight());
                gameFrame.setLayout(null);
                minePnl = new JPanel();
                gameFrame.add(minePnl);
                minePnl.setLayout(null);
                minePnl.setBounds(10, 150, 70 * Field.getWidth(), 70 * Field.getHeight());
                gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                operatingField(1);
                gameFrame.setResizable(false);
                gameFrame.repaint();
                gameFrame.setVisible(true);
            }
        });
    }

    public void operatingField(int h){
        //better than two separate methods
        for (int i = 0; i < Field.getHeight(); i++) {
            for (int j = 0; j < Field.getWidth(); j++) {
                if (h == 1) {
                    minePnl.add(mineField.field[i][j]);
                } else if(h == 2) {
                    minePnl.remove(mineField.field[i][j]);
                }
            }
        }
    }
}

