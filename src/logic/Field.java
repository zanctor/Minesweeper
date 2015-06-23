package logic;

import swing.Window;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class Field {
    // some fields are the general abstract parameters of the game(ex. isLose or isWin)
    private boolean isWin, isLose;
    private int remainingMines;
    private int width, height, minesNumber;
    public Cell field[][] = new Cell[30][30];
    Random random = new Random();

    public Field(int width, int height, int minesNumber) {
        setWidth(width);
        setHeight(height);
        setMinesNumber(minesNumber);
        generateField();
    }

    public int getRemainingMines() {
        return remainingMines;
    }

    public void setRemainingMines(int remainingMines) {
        this.remainingMines = remainingMines;
    }

    public int getMinesNumber() {
        return minesNumber;
    }

    public void setMinesNumber(int minesNumber) {
        this.minesNumber = minesNumber;
    }

    public boolean getIsLose() {
        return isLose;
    }

    public void setIsLose(boolean isLose) {
        this.isLose = isLose;
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

    private void generateField() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                //filling of the buttons array
                field[i][j] = new Cell(i, j);
                field[i][j].setBounds(40 * i, 40 * j, 40, 40);
            }
        }
        generateMines();
        generateListeners();
    }

    public void generateListeners() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                final int finalI = i;
                final int finalJ = j;
                field[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1){
                            if(field[finalI][finalJ].getIsMine()) {
                                wipeField();
                                setIsLose(true);
                                JOptionPane.showMessageDialog(Window.gameFrame, "Game over!");
                            }
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {

                            if (!field[finalI][finalJ].getIsFlag()) {
                                field[finalI][finalJ].setText("f");
                                field[finalI][finalJ].setIsFlag(true);
                            } else if (field[finalI][finalJ].getIsFlag()) {
                                if (!field[finalI][finalJ].getIsMine()) {
                                    field[finalI][finalJ].setText("");
                                    field[finalI][finalJ].setIsFlag(false);
                                } else if (field[finalI][finalJ].getIsMine()) {
                                    field[finalI][finalJ].setText("*");
                                    field[finalI][finalJ].setIsFlag(false);
                                }
                            }

                        }
                    }
                });
            }
        }
    }

    private void generateMines() {
        int i, j;
        for (int o = 0; o < getMinesNumber(); o++) {
            i = random.nextInt(getWidth());
            j = random.nextInt(getHeight());
            field[i][j].setIsMine(true);
            field[i][j].setText("*");
        }
    }

    private void generateNumbers() {

    }

    public void wipeField() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                field[i][j].setEnabled(false);
            }
        }

    }
}
