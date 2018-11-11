package result;

public class SolutionInformation {
    private int solutionLength;
    private String solutionMoves;

    public SolutionInformation() {
    }

    public SolutionInformation(int solutionLength, String solutionMoves) {
        this.solutionLength = solutionLength;
        this.solutionMoves = solutionMoves;
    }

    public int getSolutionLength() {
        return solutionLength;
    }

    public void setSolutionLength(int solutionLength) {
        this.solutionLength = solutionLength;
    }

    public String getSolutionMoves() {
        return solutionMoves;
    }

    public void setSolutionMoves(String solutionMoves) {
        this.solutionMoves = solutionMoves;
    }
}
