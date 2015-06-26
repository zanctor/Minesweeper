
package logic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

import swing.Window;

public class Field {
    private boolean isWin, isLose;
    private int remainingMines, remainingFlags, width, height, minesNumber;
    private Cell[][] field;
    private Random random = new Random();
    private Font font = new Font("Verdana", Font.BOLD, 16);
    private ImageIcon im = new ImageIcon("images/flag.png");

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

    public Cell getField(int i, int j) {
        return field[i][j];
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
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                field[i][j] = new Cell();
                getField(i, j).setFont(font);
                getField(i, j).setBounds(50 * i, 50 * j, 50, 50);
                getField(i, j).setIsEmpty(true);
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
                getField(i, j).addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        //left-clicking events
                        if (e.getButton() == MouseEvent.BUTTON1) {

                            if (getField(FinalI, FinalJ).getIsMine() && getField(FinalI, FinalJ).isEnabled()) {
                                getField(FinalI, FinalJ).setIcon(null);
                                getField(FinalI, FinalJ).setText("*");
                                getField(FinalI, FinalJ).setBackground(Color.RED);
                                for (int i = 0; i < getHeight(); i++) {
                                    for (int j = 0; j < getWidth(); j++) {
                                        if (i != FinalI || j != FinalJ) {
                                            if (getField(i, j).getIsMine()) {
                                                getField(i, j).setText("*");
                                                getField(i, j).setIcon(null);
                                                getField(i, j).setBackground(Color.YELLOW);
                                            } else if (getField(i, j).getIsNumber()) {
                                                getField(i, j).setIcon(null);
                                                getField(i, j).setText("" + Integer.toString(getField(i, j).getNumber()));
                                            }
                                        }
                                    }
                                }
                                wipeField();
                                setIsLose(true);
                                JOptionPane.showMessageDialog(Window.gameFrame, "Game over!");
                            }

                            if (getField(FinalI, FinalJ).getIsNumber()) {
                                if (getField(FinalI, FinalJ).getIsFlag()) {
                                    setRemainingFlags(getRemainingFlags() + 1);
                                }
                                getField(FinalI, FinalJ).setEnabled(false);
                                getField(FinalI, FinalJ).setIcon(null);
                                getField(FinalI, FinalJ).setText("" + getField(FinalI, FinalJ).getNumber());
                            }

                            if (getField(FinalI, FinalJ).getIsEmpty()) {
                                if (getField(FinalI, FinalJ).getIsFlag()) {
                                    setRemainingFlags(getRemainingFlags() + 1);
                                }
                                getField(FinalI, FinalJ).setIcon(null);
                                getField(FinalI, FinalJ).setEnabled(false);
                            }
                        }
                        //right-clicking events
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (!getField(FinalI, FinalJ).getIsFlag() && getField(FinalI, FinalJ).isEnabled()) {
                                if (getField(FinalI, FinalJ).getIsMine()) {
                                    getField(FinalI, FinalJ).setIsFlag(true);
                                    setRemainingMines(getRemainingMines() - 1);
                                    setRemainingFlags(getRemainingFlags() - 1);
                                    getField(FinalI, FinalJ).setIcon(im);
                                    Window.txtFlags.setText(Integer.toString(getRemainingFlags()));
                                } else if (getField(FinalI, FinalJ).getIsNumber() || getField(FinalI, FinalJ).getIsEmpty()) {
                                    getField(FinalI, FinalJ).setText("");
                                    getField(FinalI, FinalJ).setIcon(im);
                                    getField(FinalI, FinalJ).setIsFlag(true);
                                    setRemainingFlags(getRemainingFlags() - 1);
                                    Window.txtFlags.setText(Integer.toString(getRemainingFlags()));
                                }
                            } else if (getField(FinalI, FinalJ).getIsFlag() && !getField(FinalI, FinalJ).getIsMine() && getField(FinalI, FinalJ).isEnabled()) {

                                if (getField(FinalI, FinalJ).getIsNumber()) {
                                    getField(FinalI, FinalJ).setIsFlag(false);
                                    getField(FinalI, FinalJ).setIcon(null);
                                    setRemainingFlags(getRemainingFlags() + 1);
                                    Window.txtFlags.setText(Integer.toString(getRemainingFlags()));
                                } else if (getField(FinalI, FinalJ).getIsEmpty()) {
                                    getField(FinalI, FinalJ).setIcon(null);
                                    getField(FinalI, FinalJ).setIsFlag(false);
                                    setRemainingFlags(getRemainingFlags() + 1);
                                    Window.txtFlags.setText(Integer.toString(getRemainingFlags()));
                                }
                            } else if (getField(FinalI, FinalJ).getIsFlag() && getField(FinalI, FinalJ).getIsMine() && getField(FinalI, FinalJ).isEnabled()) {
                                getField(FinalI, FinalJ).setIsFlag(false);
                                getField(FinalI, FinalJ).setIcon(null);
                                setRemainingMines(getRemainingMines() + 1);
                                setRemainingFlags(getRemainingFlags() + 1);
                                Window.txtFlags.setText(Integer.toString(getRemainingFlags()));
                            }

                            if (getRemainingMines() == 0 && getRemainingFlags() == 0) {
                                for (int i = 0; i < getHeight(); i++) {
                                    for (int j = 0; j < getWidth(); j++) {
                                        if (getField(i, j).getIsMine()) {
                                            getField(i, j).setBackground(Color.GREEN);
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
        int o = 0;
        while (o < getMinesNumber()) {
            int i = random.nextInt(getHeight());
            int j = random.nextInt(getWidth());
            if (!getField(i, j).getIsMine()) {
                getField(i, j).setIsMine(true);
                getField(i, j).setIsEmpty(false);
                getField(i, j).setText("");
                o++;
            }
        }
    }

    private void generateNumbers() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                int mines = 0;
                if (getField(i, j).getIsEmpty()) {
                    if (i == 0 && j == 0) {

                        if (getField(i, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j + 1).getIsMine()) {
                            mines++;
                        }

                    } else if (i == getHeight() - 1 && j == 0) {

                        if (getField(getHeight() - 1, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(getHeight() - 2, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(getHeight() - 2, j).getIsMine()) {
                            mines++;
                        }

                    } else if (i == 0 && j == getWidth() - 1) {

                        if (getField(i + 1, getWidth() - 2).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, getWidth() - 2).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, getWidth() - 2).getIsMine()) {
                            mines++;
                        }

                    } else if (i == 0) {

                        if (getField(i + 1, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j).getIsMine()) {
                            mines++;
                        }

                    } else if (j == 0) {

                        if (getField(i - 1, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j + 1).getIsMine()) {
                            mines++;
                        }

                    } else if (i == getHeight() - 1 && j == getWidth() - 1) {

                        if (getField(i - 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j - 1).getIsMine()) {
                            mines++;
                        }

                    } else if (i == getHeight() - 1) {

                        if (getField(i, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j + 1).getIsMine()) {
                            mines++;
                        }

                    } else if (j == getWidth() - 1) {

                        if (getField(i + 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j).getIsMine()) {
                            mines++;
                        }

                    } else {

                        if (getField(i - 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j).getIsMine()) {
                            mines++;
                        }

                        if (getField(i, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j + 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i + 1, j - 1).getIsMine()) {
                            mines++;
                        }

                        if (getField(i - 1, j + 1).getIsMine()) {
                            mines++;
                        }
                    }
                }

                if (mines != 0) {
                    getField(i, j).setIsNumber(true);
                    getField(i, j).setNumber(mines);
                    getField(i, j).setIsEmpty(false);
                }
            }
        }
    }

    public void wipeField() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                getField(i, j).setEnabled(false);
            }
        }
    }
}
