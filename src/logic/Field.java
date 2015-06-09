package logic;

import javax.swing.*;

import java.util.*;

public class Field {
    public static final JPanel panel = new JPanel();
    private boolean isWin;
    private static int width;
    private static int height;
    List field[][];
    Random random = new Random();

    public Field(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static int getHeight() {
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
                field[i][j].add(new Cell(String.valueOf(random.nextInt(8) + 1)));
            }
        }
    }
    void createBattleField(){
        for (int i = 0; i <= getWidth() ; i++){
            for (int j = 0; j <= getHeight() ; j++){
                panel.add((Cell)field[i][j]);
            }
        }
    }

}
