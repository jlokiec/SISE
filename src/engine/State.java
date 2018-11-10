package engine;

public class State {
    private byte sizeX;
    private byte sizeY;
    private byte[] stateArray;

    public byte getSizeX() {
        return sizeX;
    }

    public void setSizeX(byte sizeX) {
        this.sizeX = sizeX;
    }

    public byte getSizeY() {
        return sizeY;
    }

    public void setSizeY(byte sizeY) {
        this.sizeY = sizeY;
    }

    public byte[] getStateArray() {
        return stateArray;
    }

    public void setStateArray(byte[] stateArray) {
        this.stateArray = stateArray;
    }

    @Override
    public String toString() {
        String str = "";

        for (int row = 0; row < sizeY; row++) {
            for (int column = 0; column < sizeX; column++) {
                str = str.concat(String.valueOf(stateArray[row * sizeX + column]) + " ");
            }
            str = str.concat("\n");
        }

        return str;
    }
}
