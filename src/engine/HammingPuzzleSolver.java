package engine;

import result.ExtraInformation;
import result.SolutionInformation;

public class HammingPuzzleSolver implements PuzzleSolver {
    private State currentState;
    private long startTimestamp;
    private long endTimestamp;

    public HammingPuzzleSolver(State initialState) {
        currentState = initialState;
    }

    @Override
    public void solve() {
        startTimestamp = System.nanoTime();

        // TODO: implement solving algorithm

        endTimestamp = System.nanoTime();
    }

    @Override
    public ExtraInformation getExtraInformation() {
        return null;
    }

    @Override
    public SolutionInformation getSolutionInformation() {
        return null;
    }
}
