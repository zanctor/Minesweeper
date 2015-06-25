
package logic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

import swing.Window;

public class Field {
    private boolean isWin, isLose;
    private int remainingMines, remainingFlags;
    private static int width, height, minesNumber;
    public Cell[][] field;
    Random random = new Random();
    Font font = new Font("Verdana", Font.BOLD, 16);
    ImageIcon im = new ImageIcon("images/flag.png");

    public Field(int width, int height, int minesNumber) {
        setWidth(width);
        setHeight(height);
        field = new Cell[getHeight()][getWidth()];
        setMinesNumber(minesNumber);
        setRemainingFlags(getMinesNumber());
        setRemainingMines(getMinesNumber());
        generateField();
        Window.txtFlags.setText(Integer.toString(getRemainingFlags()));
    }

    public int getRemainingFlags() {
        return remainingFlags;
    }

    public void setRemainingFlags(int remainingFlags) {
        this.remainingFlags = remainingFlags;
    }

    public int getRemainingMines() {
        return remainingMines;
    }

    public void setRemainingMines(int remainingMines) {
        this.remainingMines = remainingMines;
    }

    public static int getMinesNumber() {
        return minesNumber;
    }

    public static void setMinesNumber(int minesNumber) {
        Field.minesNumber = minesNumber;
    }

    public boolean getIsLose() {
        return isLose;
    }

    public void setIsLose(boolean isLose) {
        this.isLose = isLose;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Field.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Field.height = height;
    }

    public boolean getIsWin() {
        return isWin;
    }

    public void setIsWin(boolean isWin) {
        this.isWin = isWin;
    }

    private void generateField() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                field[i][j] = new Cell();
                field[i][j].setFont(font);
                field[i][j].setBounds(50 * i, 50 * j, 50, 50);
                field[i][j].setIsEmpty(true);
            }
        }
        generateMines();
        generateNumbers();
        generateListeners();
    }

    public void generateListeners() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                final int FinalI = i;
                final int FinalJ = j;
                field[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (field[FinalI][FinalJ].getIsMine()) {
                                if (field[FinalI][FinalJ].getIsFlag()) {
                                    setRemainingFlags(getRemainingFlags() + 1);
                                }
                                for (int i = 0; i < getHeight(); i++) {
                                    for (int j = 0; j < getWidth(); j++) {
                                        if (field[i][j].getIsMine()) {
                                            field[i][j].setText("*");
                                            field[i][j].setIcon(null);
                                            field[i][j].setBackground(Color.RED);
                                        }
                                    }
                                }
                                wipeField();
                                setIsLose(true);
                                JOptionPane.showMessageDialog(Window.gameFrame, "Game over!");
                            }

                            if (field[FinalI][FinalJ].getIsNumber()) {
                                if (field[FinalI][FinalJ].getIsFlag()) {
                                    setRemainingFlags(getRemainingFlags() + 1);
                                }
                                field[FinalI][FinalJ].setEnabled(false);
                                field[FinalI][FinalJ].setIcon(null);
                                field[FinalI][FinalJ].setText("" + field[FinalI][FinalJ].getNumber());
                            }

                            if (field[FinalI][FinalJ].getIsEmpty()) {
                                if (field[FinalI][FinalJ].getIsFlag()) {
                                    setRemainingFlags(getRemainingFlags() + 1);
                                }
                                field[FinalI][FinalJ].setIcon(null);
                                field[FinalI][FinalJ].setEnabled(false);
                            }
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (!field[FinalI][FinalJ].getIsFlag()) {
                                if (field[FinalI][FinalJ].getIsMine()) {
                                    field[FinalI][FinalJ].setIsFlag(true);
                                    setRemainingMines(getRemainingMines() - 1);
                                    setRemainingFlags(getRemainingFlags() - 1);
                                    field[FinalI][FinalJ].setIcon(im);
                                    Window.txtFlags.setText(Integer.toString(getRemainingFlags()));
                                } else if (field[FinalI][FinalJ].getIsNumber() || field[FinalI][FinalJ].getIsEmpty()) {
                                    field[FinalI][FinalJ].setText("");
                                    field[FinalI][FinalJ].setIcon(im);
                                    field[FinalI][FinalJ].setIsFlag(true);
                                    setRemainingFlags(getRemainingFlags() - 1);
                                    Window.txtFlags.setText( Integer.toString(getRemainingFlags()));
                                }
                            } else if (field[FinalI][FinalJ].getIsFlag() && !field[FinalI][FinalJ].getIsMine()) {

                                if (field[FinalI][FinalJ].getIsNumber()) {
                                    field[FinalI][FinalJ].setIsFlag(false);
                                    field[FinalI][FinalJ].setIcon(null);
                                    setRemainingFlags(getRemainingFlags() + 1);
                                    Window.txtFlags.setText( Integer.toString(getRemainingFlags()));
                                } else if (field[FinalI][FinalJ].getIsEmpty()) {
                                    field[FinalI][FinalJ].setIcon(null);
                                    field[FinalI][FinalJ].setIsFlag(false);
                                    setRemainingFlags(getRemainingFlags() + 1);
                                    Window.txtFlags.setText( Integer.toString(getRemainingFlags()));
                                }
                            } else if (field[FinalI][FinalJ].getIsFlag() && field[FinalI][FinalJ].getIsMine()) {
                                field[FinalI][FinalJ].setText("");
                                field[FinalI][FinalJ].setIsFlag(false);
                                field[FinalI][FinalJ].setIcon(null);
                                setRemainingMines(getRemainingMines() + 1);
                                setRemainingFlags(getRemainingFlags() + 1);
                                Window.txtFlags.setText( Integer.toString(getRemainingFlags()));
                            }

                            if (getRemainingMines() == 0 && getRemainingFlags() == 0) {
                                for (int i = 0; i < getHeight(); i++) {
                                    for (int j = 0; j < getWidth(); j++) {
                                        if (field[i][j].getIsMine()) {
                                            field[i][j].setBackground(Color.GREEN);
                                        }
                                    }
                                }
                                setIsWin(true);
                                wipeField();
                                JOptionPane.showMessageDialog(Window.gameFrame, "You are victorious!");
                            }
                        }

                    }
                });
            }
        }
    }


    private void generateMines() {
        for (int o = 0; o < getMinesNumber(); o++) {
            int i = random.nextInt(getHeight());
            int j = random.nextInt(getWidth());
            field[i][j].setIsMine(true);
            field[i][j].setIsEmpty(false);
            field[i][j].setText("");
        }

    }

    private void generateNumbers() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                int mines = 0;
                if (field[i][j].getIsEmpty()) {
                    if (i == 0 && j == 0) {
                        if (field[i][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j + 1].getIsMine()) {
                            ++mines;
                        }
                    } else if (i == getHeight() - 1 && j == 0) {
                        if (field[getHeight() - 1][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j].getIsMine()) {
                            ++mines;
                        }
                    } else if (i == 0 && j == getWidth() - 1) {
                        if (field[i + 1][getWidth() - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i][getWidth() - 2].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][getWidth() - 2].getIsMine()) {
                            ++mines;
                        }
                    } else if (i == 0) {
                        if (field[i + 1][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j].getIsMine()) {
                            ++mines;
                        }
                    } else if (j == 0) {
                        if (field[i - 1][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i - 1][j].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i][j + 1].getIsMine()) {
                            ++mines;
                        }
                    } else if (i == getHeight() - 1 && j == getWidth() - 1) {
                        if (field[getHeight() - 2][getWidth() - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 1][getWidth() - 2].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][getWidth() - 2].getIsMine()) {
                            ++mines;
                        }
                    } else if (i == getHeight() - 1) {
                        if (field[getHeight() - 1][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 1][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j + 1].getIsMine()) {
                            ++mines;
                        }
                    } else if (j == getWidth() - 1) {
                        if (field[getHeight() - 1][j].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 1][j - 2].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j - 2].getIsMine()) {
                            ++mines;
                        }

                        if (field[getHeight() - 2][j].getIsMine()) {
                            ++mines;
                        }
                    } else {
                        if (field[i - 1][j].getIsMine()) {
                            ++mines;
                        }

                        if (field[i][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j].getIsMine()) {
                            ++mines;
                        }

                        if (field[i][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i - 1][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j + 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i + 1][j - 1].getIsMine()) {
                            ++mines;
                        }

                        if (field[i - 1][j + 1].getIsMine()) {
                            ++mines;
                        }
                    }
                }

                if (mines != 0) {
                    field[i][j].setIsNumber(true);
                    field[i][j].setNumber(mines);
                    field[i][j].setIsEmpty(false);
                }
            }
        }

    }

    public void wipeField() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                field[i][j].setEnabled(false);
            }
        }

    }
}
