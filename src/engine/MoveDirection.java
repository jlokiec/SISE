package engine;

public enum MoveDirection {
    LEFT("L"), RIGHT("R"), UP("U"), DOWN("D"), NO_MOVE("NO");

    private final String directionShort;

    MoveDirection(String directionShort) {
        this.directionShort = directionShort;
    }

    @Override
    public String toString() {
        return directionShort;
    }
}
