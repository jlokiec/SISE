package engine;

import result.ExtraInformation;
import result.SolutionInformation;

public interface PuzzleSolver {
    void solve();

    ExtraInformation getExtraInformation();

    SolutionInformation getSolutionInformation();
}
