package swing;

import javax.swing.*;

public class Window extends JFrame{
    final JFrame frame = new JFrame();

    public void gameStart(){
        setWindow();
        frame.setVisible(true);
    }

    public void setWindow(){
        frame.setSize(400,400);

    }

}
