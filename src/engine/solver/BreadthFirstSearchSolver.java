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
     * Lista stanów otwartych czyli takich, które nie są jeszcze w pełni przeszukane (inaczej zwane frontier)
     */
    protected LinkedList<State> listOfOpenStates;

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


    public BreadthFirstSearchSolver(State initialState, MoveOrder moveOrder) {
        stateFactory = new StateFactory(initialState.getSizeX(), initialState.getSizeY());
        goalState = stateFactory.getSolvedState();
        currentState = initialState;
        listOfOpenStates = new LinkedList<>();
        listOfClosedStates = new LinkedHashSet<>();
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
    public void solve() {
        int visitedStates = 0;
        int processedStates = 0;
        long startTimestamp = System.nanoTime();

        listOfOpenStates.addFirst(currentState);

        while (!listOfOpenStates.isEmpty()) {
            currentState = listOfOpenStates.pollFirst();
            processedStates++;

            if (currentState.getDepthLevel() > maxDepth)
                maxDepth = currentState.getDepthLevel();

            if (isSolved(currentState)) {
                setInformation(currentState, startTimestamp, visitedStates, processedStates);
                return; // Success
            }

            if (currentState.getDepthLevel() < MAXIMUM_RECURSION_DEPTH) {
                Queue<State> neighbors = stateFactory.getNeighbors(currentState, moveStrategy);
                for (State neighbor : neighbors) {
                    if (listOfClosedStates.contains(neighbor)) {
                        neighbors.remove(neighbor);
                    }
                    if (isSolved(neighbor)) {
                        if (neighbor.getDepthLevel() > maxDepth)
                            maxDepth = neighbor.getDepthLevel();

                        setInformation(neighbor, startTimestamp, visitedStates, processedStates);
                        return; // Success
                    }
                    listOfClosedStates.add(currentState);
                    visitedStates++;
                    listOfOpenStates.addLast(neighbor);
                }
            }
        }
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
