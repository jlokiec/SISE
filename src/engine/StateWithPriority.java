package engine;

public class StateWithPriority implements Comparable<StateWithPriority> {
    private State state;
    private int priority;

    public StateWithPriority(State state, int priority) {
        this.state = state;
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    @Override
    public int compareTo(StateWithPriority o) {
        return Integer.compare(priority, o.priority);
    }
}
