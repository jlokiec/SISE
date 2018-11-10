package engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class State {
    private byte sizeX;
    private byte sizeY;
    private byte[] stateArray;
    private byte zeroIndex;

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

    public void findZeroIndex() {
        for (byte i = 0; i < sizeX * sizeY; i++) {
            if (stateArray[i] == 0) {
                zeroIndex = i;
                return;
            }
        }
    }

    public byte getZeroX() {
        return (byte) (zeroIndex % sizeX);
    }

    public byte getZeroY() {
        return (byte) ((zeroIndex - getZeroX()) / sizeX);
    }

    public List<MoveDirection> getAvailableMoves() {
        List<MoveDirection> availableMoves = new ArrayList<>();

        // update zero index value before checking available move directions
        findZeroIndex();

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

    Queue<State> getNeighbors() {
        LinkedList<State> neighbors = new LinkedList<>();
        List<MoveDirection> possibleDirections = getAvailableMoves();

        for (MoveDirection direction : possibleDirections) {
            State neighbor = new State();
            neighbor.setStateArray(this.getStateArray());
            //neighbor.move(direction);
            neighbors.add(neighbor);
        }
        return neighbors;
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
