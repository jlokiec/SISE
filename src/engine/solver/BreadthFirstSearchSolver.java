package engine.solver;

import engine.MoveDirection;
import engine.State;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.*;

public class BreadthFirstSearchSolver implements PuzzleSolver {

    /**
     * Stan docelowy
     */
    protected State goalState;

    /**
     * Stan aktualny
     */
    protected State actualState;

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

    public State getGoalState() {
        return goalState;
    }

    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }

    public State getActualState() {
        return actualState;
    }

    public void setActualState(State actualState) {
        this.actualState = actualState;
    }

    public LinkedList<State> getListOfOpenStates() {
        return listOfOpenStates;
    }

    public void setListOfOpenStates(LinkedList<State> listOfOpenStates) {
        this.listOfOpenStates = listOfOpenStates;
    }

    public LinkedList<MoveDirection> getDirections() {
        return directions;
    }

    public void setDirections(LinkedList<MoveDirection> directions) {
        this.directions = directions;
    }

    public Set<State> getListOfClosedStates() {
        return listOfClosedStates;
    }

    public void setListOfClosedStates(Set<State> listOfClosedStates) {
        this.listOfClosedStates = listOfClosedStates;
    }

    public BreadthFirstSearchSolver(State initialState, State goalState) {
        setGoalState(goalState);
        setListOfOpenStates(new LinkedList<>());
        setListOfClosedStates(new LinkedHashSet<>());
        setDirections(new LinkedList<>());
        getListOfOpenStates().addFirst(initialState);
    }

    private boolean isSolved(State state) {
        return (getGoalState() == state ? true : false);
    }

    @Override
    public void solve() {
        while(!listOfOpenStates.isEmpty()) {
            setActualState(listOfOpenStates.poll());
            listOfClosedStates.add(actualState);

            if(isSolved(getActualState()))
                return; // Success
            }

            for (State neighbor : actualState.getNeighbors()) {
                if(!(getListOfOpenStates().contains(neighbor) || getListOfClosedStates().contains(neighbor))) {
                    getListOfOpenStates().addFirst(neighbor);
                }
            }
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
