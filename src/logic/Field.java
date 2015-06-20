package logic;

import java.util.*;

public class Field {
    private boolean isWin;
    private int width, height, mines_number;
    public  Cell field[][] = new Cell[30][30];
    Random random = new Random();

    public int getMinesNumber() {
        return mines_number;
    }

    public void setMinesNumber(int minesNumber) {
        mines_number = minesNumber;
    }

    public Field(int width, int height){
        setWidth(width);
        setHeight(height);
        generateField();
        generateMines();
    }

    public  int getWidth() {
        return width;
    }

    public void setWidth(int width) { this.width = width; }

    public  int getHeight() {
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

    private void generateField(){
        for (int i = 1; i < getWidth(); i++){
            for (int j = 1; j < getHeight(); j++){
                //filling of the buttons array
                field[i][j] = new Cell(i,j);
                field[i][j].setBounds(30*i, 30*j, 30, 30);
                // TODO make left-clicking and right-clicking listeners
            }
        }
    }

    private void generateMines() {
        for (int i = 1; i < getWidth(); i++) {
            for (int j = 1; j < getHeight(); j++) {
                if (getMinesNumber() != 0){
                    field[i][j].setIsMine(random.nextBoolean());
                    if (field[i][j].getIsMine()){
                        field[i][j].setText("*");
                        setMinesNumber(getMinesNumber() - 1);
                    }
                }
            }
        }
    }

    private void generateNumber(){

    }
}
