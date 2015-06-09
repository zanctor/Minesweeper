package swing;

import javax.swing.*;

import logic.*;

public class Window extends JFrame{
    JFrame gameFrame;
    JFrame getSizeFrame;
    JButton insertBtn;
    JTextField txtWidth, txtHeight;
    JLabel lblWidth, lblHeight;

    public void gameStart() {
        getSizeFrame = new JFrame("Width and height of the field");
        getSizeFrame.setVisible(true);
        getSizeFrame.setSize(300, 300);
        getSizeFrame.add(insertBtn = new JButton("Insert values"));
        getSizeFrame.add(lblWidth = new JLabel("Input number of cells of width: "));
        getSizeFrame.add(txtWidth = new JTextField());
        getSizeFrame.add(lblHeight = new JLabel("Input number of cells of height: "));
        getSizeFrame.add(txtHeight = new JTextField());
        if (getWidth() != 0 && getHeight() != 0) {
            gameFrame = new JFrame();
            gameFrame.setTitle("Minesweeper");
            gameFrame.setVisible(true);
            setWindow();
        }
    }
    public void setWindow(){
        gameFrame.setSize(400,400);
    }



}
