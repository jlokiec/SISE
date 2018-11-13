package cli;

import engine.State;
import engine.heuristic.HammingHeuristic;
import engine.heuristic.ManhattanHeuristic;
import engine.solver.AStarPuzzleSolver;
import engine.solver.PuzzleSolver;
import input.InitialStateReader;
import result.ExtraInformation;
import result.ExtraInformationSaver;
import result.SolutionInformation;
import result.SolutionInformationSaver;

public class ConsoleProgram {
    private static final String BFS_STRATEGY = "bfs";
    private static final String DFS_STRATEGY = "dfs";
    private static final String A_STAR_STRATEGY = "astr";

    private static final String HAMMING_HEURISTIC = "hamm";
    private static final String MANHATTAN_HEURISTIC = "manh";

    public static void main(String[] args) {
        if (args.length == 5) {
            String selectedStrategy = args[0];
            String selectedStrategyExtra = args[1];
            String inputFile = args[2];
            String outputSolutionFile = args[3];
            String outputExtraFile = args[4];

            System.out.print(inputFile);

            State initialState = readInitialStateFromFile(inputFile);

            PuzzleSolver puzzleSolver = null;

            // TODO: implement running selected strategy
            switch (selectedStrategy) {
                case BFS_STRATEGY:
                    // TODO: implement running BFS
                    break;
                case DFS_STRATEGY:
                    // TODO: implement running DFS
                    break;
                case A_STAR_STRATEGY:
                    if (selectedStrategyExtra.equals(HAMMING_HEURISTIC)) {
                        puzzleSolver = new AStarPuzzleSolver(initialState, new HammingHeuristic());
                    }
                    if (selectedStrategyExtra.equals(MANHATTAN_HEURISTIC)) {
                        puzzleSolver = new AStarPuzzleSolver(initialState, new ManhattanHeuristic());
                    }
                    break;
                default:
                    System.out.println("Selected invalid strategy");
            }

            if (puzzleSolver == null) {
                System.out.println("puzzleSolver is null");
                return;
            }

            puzzleSolver.solve();

            saveSolutionInformation(outputSolutionFile, puzzleSolver.getSolutionInformation());
            saveExtraInformation(outputExtraFile, puzzleSolver.getExtraInformation());

            System.out.println(" solved");
        } else {
            System.out.println("Passed incorrect number of arguments");
        }
    }

    private static State readInitialStateFromFile(String fileName) {
        InitialStateReader initialStateReader = new InitialStateReader(fileName);
        initialStateReader.read();
        return initialStateReader.getInitialState();
    }

    private static void saveSolutionInformation(String fileName, SolutionInformation solutionInformation) {
        SolutionInformationSaver saver = new SolutionInformationSaver(fileName, solutionInformation);
        saver.save();
    }

    private static void saveExtraInformation(String fileName, ExtraInformation extraInformation) {
        ExtraInformationSaver saver = new ExtraInformationSaver(fileName, extraInformation);
        saver.save();
    }
}
