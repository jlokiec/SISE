package engine.solver;

import result.ExtraInformation;
import result.SolutionInformation;

public interface PuzzleSolver {
    boolean solve();

    ExtraInformation getExtraInformation();

    SolutionInformation getSolutionInformation();
}
