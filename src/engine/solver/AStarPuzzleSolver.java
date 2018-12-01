package engine.solver;

import engine.MoveDirection;
import engine.State;
import engine.StateFactory;
import engine.StateWithPriority;
import engine.heuristic.Heuristic;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.*;

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

    private boolean isSolved(State currentState) {
        return Arrays.equals(currentState.getStateArray(), solvedState.getStateArray());
    }

    @Override
    public boolean solve() {
        int maxDepth = 0;
        int visitedStates = 0;
        int processedStates = 0;
        long startTimestamp = System.nanoTime();

        Queue<StateWithPriority> frontier = new PriorityQueue<>();
        frontier.add(new StateWithPriority(currentState, 0));

        Set<State> explored = new HashSet<>();

        while (!frontier.isEmpty()) {
            currentState = frontier.poll().getState();

            if (currentState.getDepthLevel() > maxDepth) {
                maxDepth = currentState.getDepthLevel();
            }

            processedStates++;

            if (isSolved(currentState)) {
                long endTimestamp = System.nanoTime();

                solutionInformation.setSolutionLength(currentState.getDepthLevel());
                solutionInformation.setSolutionMoves(currentState.getPath());

                extraInformation.setVisitedStates(visitedStates);
                extraInformation.setProcessedStates(processedStates);
                extraInformation.setMaxRecursionDepth(maxDepth);
                extraInformation.setSolutionLength(currentState.getDepthLevel());

                double computationTime = (endTimestamp - startTimestamp) / 100000.0;
                extraInformation.setComputationTime(computationTime);

                return true;
            } else {
                explored.add(currentState);
            }

            for (MoveDirection moveDirection : currentState.getAvailableMoves()) {
                State stateAfterMove = stateFactory.getStateAfterMove(currentState, moveDirection);

                if (isSolved(stateAfterMove)) {
                    if (stateAfterMove.getDepthLevel() > maxDepth) {
                        maxDepth = stateAfterMove.getDepthLevel();
                    }

                    long endTimestamp = System.nanoTime();

                    solutionInformation.setSolutionLength(stateAfterMove.getDepthLevel());
                    solutionInformation.setSolutionMoves(stateAfterMove.getPath());

                    extraInformation.setVisitedStates(visitedStates);
                    extraInformation.setProcessedStates(processedStates);
                    extraInformation.setMaxRecursionDepth(maxDepth);
                    extraInformation.setSolutionLength(stateAfterMove.getDepthLevel());

                    double computationTime = (endTimestamp - startTimestamp) / 100000.0;
                    extraInformation.setComputationTime(computationTime);

                    return true;
                }

                if (!explored.contains(stateAfterMove)) {
                    frontier.add(new StateWithPriority(stateAfterMove, heuristic.getValue(stateAfterMove, solvedState)));
                }

                visitedStates++;
            }
        }

        // set information for failure
        long endTimestamp = System.nanoTime();

        solutionInformation.setSolutionLength(-1);
        solutionInformation.setSolutionMoves("");

        extraInformation.setVisitedStates(visitedStates);
        extraInformation.setProcessedStates(processedStates);
        extraInformation.setMaxRecursionDepth(maxDepth);
        extraInformation.setSolutionLength(0);

        double computationTime = (endTimestamp - startTimestamp) / 100000.0;
        extraInformation.setComputationTime(computationTime);

        return false;
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
