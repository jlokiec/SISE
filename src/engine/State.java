package engine;

import java.util.ArrayList;
import java.util.List;

public class State {
    private byte sizeX;
    private byte sizeY;
    private byte[] stateArray;
    private State parentState;
    private MoveDirection previousMove;
    private int depthLevel;

    public State(byte sizeX, byte sizeY, byte[] stateArray, State parentState, MoveDirection previousMove, int depthLevel) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.stateArray = stateArray;
        this.parentState = parentState;
        this.previousMove = previousMove;
        this.depthLevel = depthLevel;
    }

    public byte getSizeX() {
        return sizeX;
    }

    public byte getSizeY() {
        return sizeY;
    }

    public byte[] getStateArray() {
        return stateArray;
    }

    public byte getZeroIndex() {
        for (byte i = 0; i < sizeX * sizeY; i++) {
            if (stateArray[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public String getPath() {
        String path = "";

        if (parentState == null) {
            return path;
        }

        path = path.concat(parentState.getPath() + previousMove);

        return path;
    }

    public byte getZeroX() {
        return (byte) (getZeroIndex() % sizeX);
    }

    public byte getZeroY() {
        return (byte) ((getZeroIndex() - getZeroX()) / sizeX);
    }

    public List<MoveDirection> getAvailableMoves() {
        List<MoveDirection> availableMoves = new ArrayList<>();

        if (getZeroY() > 0) {
            availableMoves.add(MoveDirection.UP);
        }
        if (getZeroY() < sizeY - 1) {
            availableMoves.add(MoveDirection.DOWN);
        }
        if (getZeroX() > 0) {
            availableMoves.add(MoveDirection.LEFT);
        }
        if (getZeroX() < sizeX - 1) {
            availableMoves.add(MoveDirection.RIGHT);
        }

        return availableMoves;
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
