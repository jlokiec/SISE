package result;

public class ExtraInformation {
    private int solutionLength;
    private int visitedStates;
    private int processedStates;
    private int maxRecursionDepth;
    private double computationTime;

    public ExtraInformation(int solutionLength, int visitedStates, int processedStates, int maxRecursionDepth, double computationTime) {
        this.solutionLength = solutionLength;
        this.visitedStates = visitedStates;
        this.processedStates = processedStates;
        this.maxRecursionDepth = maxRecursionDepth;
        this.computationTime = computationTime;
    }

    public int getSolutionLength() {
        return solutionLength;
    }

    public int getVisitedStates() {
        return visitedStates;
    }

    public int getProcessedStates() {
        return processedStates;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public double getComputationTime() {
        return computationTime;
    }
}
