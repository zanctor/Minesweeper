package logic;

public class Cell {
    private boolean isMine;
    private boolean isEmpty;
    private boolean isFlag;
    private int number;

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
