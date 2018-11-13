package engine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StateFactory {
    private byte sizeX;
    private byte sizeY;

    public StateFactory(byte sizeX, byte sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public State getSolvedState() {
        byte[] solvedStateArray = new byte[sizeX * sizeY];
        for (byte i = 0; i < solvedStateArray.length - 1; i++) {
            solvedStateArray[i] = (byte) (i + 1);
        }

        return new State(sizeX, sizeY, solvedStateArray, null, MoveDirection.NO_MOVE, 0);
    }

    public State getStateAfterMove(State currentState, MoveDirection moveDirection) {
        byte zeroIndex = currentState.getPuzzleIndex(State.ZERO_PUZZLE);
        byte indexToSwap = -1;

        switch (moveDirection) {
            case DOWN:
                indexToSwap = (byte) (zeroIndex + currentState.getSizeX());
                break;
            case UP:
                indexToSwap = (byte) (zeroIndex - currentState.getSizeX());
                break;
            case LEFT:
                indexToSwap = (byte) (zeroIndex - 1);
                break;
            case RIGHT:
                indexToSwap = (byte) (zeroIndex + 1);
                break;
        }

        byte[] swappedStateArray = swap(currentState.getStateArray(), zeroIndex, indexToSwap);

        return new State(sizeX, sizeY, swappedStateArray, currentState, moveDirection, currentState.getDepthLevel() + 1);
    }

    public Queue<State> getNeighbors(State currentState) {
        LinkedList<State> neighbors = new LinkedList<>();
        List<MoveDirection> possibleDirections = currentState.getAvailableMoves();

        for (MoveDirection direction : possibleDirections) {
            State neighbor = getStateAfterMove(currentState, direction);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    private byte[] swap(byte[] array, byte index1, byte index2) {
        byte[] swappedArray = Arrays.copyOf(array, array.length);

        byte temp = swappedArray[index1];
        swappedArray[index1] = swappedArray[index2];
        swappedArray[index2] = temp;

        return swappedArray;
    }
}
