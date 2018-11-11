package engine.heuristic;

import engine.State;

public class HammingHeuristic implements Heuristic {
    @Override
    public int getValue(State processedState, State solvedState) {
        byte[] stateArray = processedState.getStateArray();
        byte[] solvedStateArray = solvedState.getStateArray();

        int distance = processedState.getDepthLevel();

        for (int i = 0; i < stateArray.length; i++) {
            if (stateArray[i] == State.ZERO_PUZZLE) {
                continue;
            }
            if (stateArray[i] != solvedStateArray[i]) {
                distance++;
            }
        }

        return distance;
    }
}
