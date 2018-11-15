package engine.solver;

import engine.MoveDirection;
import engine.MoveOrder;
import engine.State;
import engine.StateFactory;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.*;

public class BreadthFirstSearchSolver implements PuzzleSolver {
    private ExtraInformation extraInformation;
    private SolutionInformation solutionInformation;

    /**
     * Stan docelowy
     */
    protected State goalState;

    /**
     * Stan aktualny
     */
    protected State currentState;

    /**
     * Wybrana strategia przeszukiwania
     */
    protected MoveOrder moveStrategy;

    /**
     * Lista stanów otwartych czyli takich, które nie są jeszcze w pełni przeszukane
     */
    protected LinkedList<State> listOfOpenStates;

    /**
     * Lista stanów zamkniętych czyli takich, które są już w całości przeszukane
     */
    protected Set<State> listOfClosedStates;

    /**
     * Ciąg operatorów, które od stanu początkowego przejdą do stanu docelowego
     */
    protected LinkedList<MoveDirection> directions;

    /**
     * Referencja do fabryki stanów układanki
     */
    protected StateFactory stateFactory;

    public BreadthFirstSearchSolver(State initialState, MoveOrder moveOrder) {
        stateFactory = new StateFactory(initialState.getSizeX(), initialState.getSizeY());
        goalState = stateFactory.getSolvedState();
        currentState = initialState;
        listOfOpenStates = new LinkedList<>();
        listOfClosedStates = new LinkedHashSet<>();
        directions = new LinkedList<>();
        moveStrategy = moveOrder;

        extraInformation = new ExtraInformation();
        solutionInformation = new SolutionInformation();
    }

    private boolean isSolved() {
        return (Arrays.equals(currentState.getStateArray(), goalState.getStateArray()));
    }

    @Override
    public void solve() {
        int visitedStates = 0;
        long startTimestamp = System.nanoTime();

        listOfOpenStates.addFirst(currentState);
        visitedStates++;

        while (!listOfOpenStates.isEmpty()) {
            currentState = listOfOpenStates.pollFirst();
            listOfClosedStates.add(currentState);

            if (isSolved()) {
                long endTimestamp = System.nanoTime();

                solutionInformation.setSolutionLength(currentState.getDepthLevel());
                solutionInformation.setSolutionMoves(currentState.getPath());

                extraInformation.setVisitedStates(visitedStates);
                extraInformation.setProcessedStates(listOfClosedStates.size());
                extraInformation.setMaxRecursionDepth(currentState.getDepthLevel());
                extraInformation.setSolutionLength(currentState.getDepthLevel());

                double computationTime = (endTimestamp - startTimestamp) / 100000.0;
                extraInformation.setComputationTime(computationTime);
                return; // Success
            }

            for (State neighbor : stateFactory.getNeighbors(currentState, moveStrategy)) {
                if(currentState.getDepthLevel() > 20)
                    break;
                if (!(listOfOpenStates.contains(neighbor) || listOfClosedStates.contains(neighbor))) {
                    visitedStates++;
                    listOfOpenStates.addLast(neighbor);
                }
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
