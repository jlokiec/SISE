package engine.heuristic;

import engine.State;

public class ManhattanHeuristic implements Heuristic {
    @Override
    public int getValue(State processedState, State solvedState) {
        byte[] stateArray = processedState.getStateArray();
        byte sizeX = processedState.getSizeX();
        byte sizeY = processedState.getSizeY();

        int distance = processedState.getDepthLevel();

        for (int height = 0; height < sizeY; height++) {
            for (int width = 0; width < sizeX; width++) {
                byte puzzleValue = stateArray[width + height * sizeX];

                if (puzzleValue != State.ZERO_PUZZLE) {
                    int x = (puzzleValue - 1) % sizeX;
                    int y = (puzzleValue - 1) / sizeY;

                    distance += Math.abs(width - x) + Math.abs(height - y);
                }
            }
        }

        return distance;
    }
}
