package engine.solver;

import engine.MoveDirection;
import engine.MoveOrder;
import engine.State;
import engine.StateFactory;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.*;

public class DepthFirstSearchSolver implements PuzzleSolver {
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
     * Lista stanów otwartych czyli takich, które nie są jeszcze w pełni przeszukane (inaczej zwane frontier)
     */
    protected Stack<State> listOfOpenStates;

    /**
     * Lista stanów zamkniętych czyli takich, które są już w całości przeszukane (inaczej zwane explored)
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

    /**
     * Maksymalna osiągnięta głębokość rekursji
     */
    protected int maxDepth;

    /**
     * Maksymalna głębokość rekursji
     */
    protected final int MAXIMUM_RECURSION_DEPTH = 20;

    public DepthFirstSearchSolver(State initialState, MoveOrder moveOrder) {
        stateFactory = new StateFactory(initialState.getSizeX(), initialState.getSizeY());
        goalState = stateFactory.getSolvedState();
        currentState = initialState;
        listOfOpenStates = new Stack<>();
        listOfClosedStates = new HashSet<>();
        directions = new LinkedList<>();
        moveStrategy = moveOrder;
        maxDepth = 1;

        extraInformation = new ExtraInformation();
        solutionInformation = new SolutionInformation();
    }

    private boolean isSolved(State state) {
        return (Arrays.equals(state.getStateArray(), goalState.getStateArray()));
    }

    @Override
    public boolean solve() {
        int visitedStates = 0;
        int processedStates = 0;
        long startTimestamp = System.nanoTime();

        listOfOpenStates.push(currentState);

        while (!listOfOpenStates.isEmpty()) {
            currentState = listOfOpenStates.pop();
            processedStates++;

            if (currentState.getDepthLevel() > maxDepth)
                maxDepth = currentState.getDepthLevel();

            if (isSolved(currentState)) {
                setInformation(currentState, startTimestamp, visitedStates, processedStates);
                return true; // Success
            }
            if (currentState.getDepthLevel() < MAXIMUM_RECURSION_DEPTH) {
                Queue<State> neighbors = stateFactory.getNeighborsReversed(currentState, moveStrategy);
                for (State neighbor : neighbors) {
                    if (listOfClosedStates.contains(neighbor)) {
                        continue;
                    }
                    if (isSolved(neighbor)) {
                        if (neighbor.getDepthLevel() > maxDepth)
                            maxDepth = neighbor.getDepthLevel();

                        setInformation(neighbor, startTimestamp, visitedStates, processedStates);
                        return true; // Success
                    }
                    listOfClosedStates.add(currentState);
                    visitedStates++;
                    listOfOpenStates.push(neighbor);
                }
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

    private void setInformation(State state, long startTimestamp, int visitedStates, int processedStates) {
        long endTimestamp = System.nanoTime();

        solutionInformation.setSolutionLength(state.getDepthLevel());
        solutionInformation.setSolutionMoves(state.getPath());

        extraInformation.setVisitedStates(visitedStates);
        extraInformation.setProcessedStates(processedStates);
        extraInformation.setMaxRecursionDepth(maxDepth);
        extraInformation.setSolutionLength(state.getDepthLevel());

        double computationTime = (endTimestamp - startTimestamp) / 100000.0;
        extraInformation.setComputationTime(computationTime);
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
