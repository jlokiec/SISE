package result;

public class ExtraInformation {
    private int solutionLength;
    private int visitedStates;
    private int processedStates;
    private int maxRecursionDepth;
    private double computationTime;

    public ExtraInformation() {
    }

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

    public void setSolutionLength(int solutionLength) {
        this.solutionLength = solutionLength;
    }

    public int getVisitedStates() {
        return visitedStates;
    }

    public void setVisitedStates(int visitedStates) {
        this.visitedStates = visitedStates;
    }

    public int getProcessedStates() {
        return processedStates;
    }

    public void setProcessedStates(int processedStates) {
        this.processedStates = processedStates;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public void setMaxRecursionDepth(int maxRecursionDepth) {
        this.maxRecursionDepth = maxRecursionDepth;
    }

    public double getComputationTime() {
        return computationTime;
    }

    public void setComputationTime(double computationTime) {
        this.computationTime = computationTime;
    }
}
