package result;

public class SolutionInformation {
    private int solutionLength;
    private String solutionMoves;

    public SolutionInformation(int solutionLength, String solutionMoves) {
        this.solutionLength = solutionLength;
        this.solutionMoves = solutionMoves;
    }

    public int getSolutionLength() {
        return solutionLength;
    }

    public String getSolutionMoves() {
        return solutionMoves;
    }
}
