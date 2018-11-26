package engine.heuristic;

import engine.State;

public class ManhattanHeuristic implements Heuristic {
    @Override
    public int getValue(State processedState, State solvedState) {
        byte[] stateArray = processedState.getStateArray();

        int distance = processedState.getDepthLevel();

        for (int i = 0; i < stateArray.length; i++) {
            if (stateArray[i] != State.ZERO_PUZZLE) {
                byte puzzleValue = stateArray[i];
                int x = processedState.getPuzzleX(puzzleValue);
                int y = processedState.getPuzzleY(puzzleValue);
                int targetX = solvedState.getPuzzleX(puzzleValue);
                int targetY = solvedState.getPuzzleY(puzzleValue);

                distance += (Math.abs(x - targetX) + Math.abs(y - targetY));
            }
        }

        return distance;
    }
}
