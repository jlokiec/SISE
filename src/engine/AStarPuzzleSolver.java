package engine;

import engine.heuristic.Heuristic;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AStarPuzzleSolver implements PuzzleSolver {
    // extra information
    private long startTimestamp;
    private long endTimestamp;
    private int visitedStates;
    private int processedStates;
    private int maxDepth;

    // solution information
    private String solutionPath;
    private int solutionLength;

    private State currentState;
    private State solvedState;
    private StateFactory stateFactory;
    private Heuristic heuristic;

    public AStarPuzzleSolver(State initialState, Heuristic heuristic) {
        currentState = initialState;

        this.heuristic = heuristic;
        stateFactory = new StateFactory(initialState.getSizeX(), initialState.getSizeY());
        solvedState = stateFactory.getSolvedState();

        solutionPath = "";
        solutionLength = 0;
        visitedStates = 0;
        processedStates = 0;
        maxDepth = 0;
    }

    private boolean isSolved() {
        return Arrays.equals(currentState.getStateArray(), solvedState.getStateArray());
    }

    @Override
    public void solve() {
        startTimestamp = System.nanoTime();

        PriorityQueue<StateWithPriority> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new StateWithPriority(currentState, 0));

        while (!isSolved()) {
            currentState = priorityQueue.poll().getState();

            if (currentState.getDepthLevel() > maxDepth) {
                maxDepth = currentState.getDepthLevel();
            }

            List<MoveDirection> availableMoves = currentState.getAvailableMoves();

            for (MoveDirection moveDirection : availableMoves) {
                State stateAfterMove = stateFactory.getStateAfterMove(currentState, moveDirection);
                priorityQueue.add(new StateWithPriority(stateAfterMove, heuristic.getValue(stateAfterMove, solvedState)));
            }
        }

        endTimestamp = System.nanoTime();

        solutionPath = currentState.getPath();
        solutionLength = solutionPath.length();
    }

    @Override
    public ExtraInformation getExtraInformation() {
        double computationTime = (endTimestamp - startTimestamp) / 1000.0;
        return new ExtraInformation(solutionLength, visitedStates, processedStates, maxDepth, computationTime);
    }

    @Override
    public SolutionInformation getSolutionInformation() {
        return new SolutionInformation(solutionLength, solutionPath);
    }
}
