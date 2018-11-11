package engine.solver;

import engine.MoveDirection;
import engine.State;
import engine.StateFactory;
import engine.StateWithPriority;
import engine.heuristic.Heuristic;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AStarPuzzleSolver implements PuzzleSolver {
    private ExtraInformation extraInformation;
    private SolutionInformation solutionInformation;

    private State currentState;
    private State solvedState;
    private StateFactory stateFactory;
    private Heuristic heuristic;

    public AStarPuzzleSolver(State initialState, Heuristic heuristic) {
        currentState = initialState;
        this.heuristic = heuristic;

        stateFactory = new StateFactory(initialState.getSizeX(), initialState.getSizeY());
        solvedState = stateFactory.getSolvedState();

        extraInformation = new ExtraInformation();
        solutionInformation = new SolutionInformation();
    }

    private boolean isSolved() {
        return Arrays.equals(currentState.getStateArray(), solvedState.getStateArray());
    }

    @Override
    public void solve() {
        int visitedStates = 0;
        long startTimestamp = System.nanoTime();

        PriorityQueue<StateWithPriority> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new StateWithPriority(currentState, 0));

        while (priorityQueue.size() > 0) {
            currentState = priorityQueue.poll().getState();

            visitedStates++;

            if (isSolved()) {
                long endTimestamp = System.nanoTime();

                solutionInformation.setSolutionLength(currentState.getDepthLevel());
                solutionInformation.setSolutionMoves(currentState.getPath());

                extraInformation.setVisitedStates(visitedStates);
                extraInformation.setProcessedStates(visitedStates + priorityQueue.size());
                extraInformation.setMaxRecursionDepth(currentState.getDepthLevel());
                extraInformation.setSolutionLength(currentState.getDepthLevel());

                double computationTime = (endTimestamp - startTimestamp) / 100000.0;
                extraInformation.setComputationTime(computationTime);

                return;
            }

            List<MoveDirection> availableMoves = currentState.getAvailableMoves();

            for (MoveDirection moveDirection : availableMoves) {
                State stateAfterMove = stateFactory.getStateAfterMove(currentState, moveDirection);
                priorityQueue.add(new StateWithPriority(stateAfterMove, heuristic.getValue(stateAfterMove, solvedState)));
            }
        }
    }

    @Override
    public ExtraInformation getExtraInformation() {
        return extraInformation;
    }

    @Override
    public SolutionInformation getSolutionInformation() {
        return solutionInformation;
    }
}
