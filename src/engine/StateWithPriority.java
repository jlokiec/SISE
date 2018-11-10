package engine;

public class StateWithPriority {
    private State state;
    private int priority;

    public StateWithPriority(State state, int priority) {
        this.state = state;
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public int getPriority() {
        return priority;
    }
}
