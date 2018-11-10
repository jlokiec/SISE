package engine;

import java.util.Arrays;

public class StateFactory {
    private byte sizeX;
    private byte sizeY;

    public StateFactory(byte sizeX, byte sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public State getSolvedState() {
        State solvedState = new State(sizeX, sizeY);

        byte[] solvedStateArray = new byte[sizeX * sizeY];
        for (byte i = 0; i < solvedStateArray.length - 1; i++) {
            solvedStateArray[i] = (byte) (i + 1);
        }
        solvedState.setStateArray(solvedStateArray);

        return solvedState;
    }

    public State getStateAfterMove(State currentState, MoveDirection moveDirection) {
        byte zeroIndex = currentState.getZeroIndex();
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

        return new State(currentState.getSizeX(), currentState.getSizeY(), swappedStateArray);
    }

    private byte[] swap(byte[] array, byte index1, byte index2) {
        byte[] swappedArray = Arrays.copyOf(array, array.length);

        byte temp = swappedArray[index1];
        swappedArray[index1] = swappedArray[index2];
        swappedArray[index2] = temp;

        return swappedArray;
    }
}
