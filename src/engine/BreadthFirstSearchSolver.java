package engine;

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
    protected Queue<State> ListOfOpenStates;

    /**
     * Lista stanów zamkniętych czyli takich, które są już w całości przeszukane
     */
    protected Set<State> ListOfClosedStates;

    /**
     * Ciąg operatorów, które od stanu początkowego przejdą do stanu docelowego
     */
    protected LinkedList<MoveDirection> Directions;

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

    public Queue<State> getListOfOpenStates() {
        return ListOfOpenStates;
    }

    public void setListOfOpenStates(Queue<State> listOfOpenStates) {
        ListOfOpenStates = listOfOpenStates;
    }

    public LinkedList<MoveDirection> getDirections() {
        return Directions;
    }

    public void setDirections(LinkedList<MoveDirection> directions) {
        Directions = directions;
    }

    public Set<State> getListOfClosedStates() {
        return ListOfClosedStates;
    }

    public void setListOfClosedStates(Set<State> listOfClosedStates) {
        ListOfClosedStates = listOfClosedStates;
    }
    public BreadthFirstSearchSolver(State initialState, State goalState) {
        setGoalState(goalState);
        setListOfOpenStates(new LinkedList<>());
        setListOfClosedStates(new LinkedHashSet<>());
        setDirections(new LinkedList<>());
        getListOfOpenStates().add(initialState);
    }

    private boolean isSolved(State actualState) {
        return (goalState == actualState ? true : false);
    }

    @Override
    public void solve() {
        while(!ListOfOpenStates.isEmpty()) {
            actualState = ListOfOpenStates.peek();
            ListOfClosedStates.add(actualState);

            if(isSolved(actualState))
                return; // Success
            }
        }
    }
