package engine.heuristic;

import engine.State;

public class ManhattanHeuristic implements Heuristic {
    @Override
    public int getValue(State processedState, State solvedState) {
        byte[] stateArray = processedState.getStateArray();

        int distance = processedState.getDepthLevel();

        for (int i = 0; i < stateArray.length; i++) {
            if (stateArray[i] == State.ZERO_PUZZLE) {
                continue;
            }

            byte puzzleValue = stateArray[i];
            byte puzzleX = processedState.getPuzzleX(puzzleValue);
            byte puzzleY = processedState.getPuzzleY(puzzleValue);
            byte solvedX = solvedState.getPuzzleX(puzzleValue);
            byte solvedY = solvedState.getPuzzleY(puzzleValue);

            distance += Math.abs(puzzleX - solvedX) + Math.abs(puzzleY - solvedY);
        }

        return distance;
    }
}
