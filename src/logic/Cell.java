package logic;

import javax.swing.*;

public class Cell extends JToggleButton {
    private boolean isMine, isEmpty, isFlag, isNumber;
    private int number, horizontal, vertical;

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    Cell(int horizontal, int vertical) {
        setHorizontal(horizontal);
        setVertical(vertical);
    }

    public boolean getIsNumber() {
        return isNumber;
    }

    public void setIsNumber(boolean isNumber) {
        this.isNumber = isNumber;
    }

    public boolean getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

}