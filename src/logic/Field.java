package logic;

import javax.swing.*;
import java.util.*;

public class Field {
    JPanel panel;
    private boolean isWin;
    int width;
    int height;
    List field[][];
    Random random = new Random();

    public Field(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean getIsWin() {
        return isWin;
    }

    public void setIsWin(boolean isWin) {
        this.isWin = isWin;
    }

    void genField(){
        for (int i = 0; i <= getWidth(); i++){
            for (int j = 0; i <= getHeight(); i++){
                //filling of List
                field[i][j].add(new JButton(String.valueOf(random.nextInt(8)+1)));
            }
        }
    }
    void createBattleField(){
        for (int i = 0; i <= getWidth() ; i++){
            for (int j = 0; j <= getHeight() ; j++){
                panel.add((JButton)field[i][j]);
            }
        }
    }

}
