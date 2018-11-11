package engine.heuristic;

import engine.State;

public interface Heuristic {
    int getValue(State processedState, State solvedState);
}
