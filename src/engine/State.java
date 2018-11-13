package engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class State {
    public static final byte ZERO_PUZZLE = 0;

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

    public byte getPuzzleIndex(byte puzzleValue) {
        for (byte i = 0; i < sizeX * sizeY; i++) {
            if (stateArray[i] == puzzleValue) {
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

    public byte getPuzzleX(byte puzzleValue) {
        return (byte) (getPuzzleIndex(puzzleValue) % sizeX);
    }

    public byte getPuzzleY(byte puzzleValue) {
        return (byte) ((getPuzzleIndex(puzzleValue) - getPuzzleX(puzzleValue)) / sizeX);
    }

    public List<MoveDirection> getAvailableMoves() {
        List<MoveDirection> availableMoves = new ArrayList<>();

        if (getPuzzleY(ZERO_PUZZLE) > 0) {
            availableMoves.add(MoveDirection.UP);
        }
        if (getPuzzleY(ZERO_PUZZLE) < sizeY - 1) {
            availableMoves.add(MoveDirection.DOWN);
        }
        if (getPuzzleX(ZERO_PUZZLE) > 0) {
            availableMoves.add(MoveDirection.LEFT);
        }
        if (getPuzzleX(ZERO_PUZZLE) < sizeX - 1) {
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
