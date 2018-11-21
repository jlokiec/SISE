package engine.solver;

import engine.MoveDirection;
import engine.MoveOrder;
import engine.State;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchSolverTest {

    @ParameterizedTest
    @MethodSource("generateDataForSolve")
    void solve(State initialState, MoveOrder moveOrder) {
        BreadthFirstSearchSolver breadthFirstSearchSolver = new BreadthFirstSearchSolver(initialState, moveOrder);
        breadthFirstSearchSolver.solve();

        assertNotNull(breadthFirstSearchSolver);
    }
    static Stream<Arguments> generateDataForSolve() {
        byte[] initialValues = { 1, 2, 3, 4, 5, 6, 7, 8, 10, 13, 11, 12, 9, 14, 0, 15};
        State initialState = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues, null, MoveDirection.NO_MOVE, 0);

        return Stream.of(
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_UP_LEFT),
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_LEFT_UP),
                Arguments.of(initialState, MoveOrder.DOWN_RIGHT_UP_LEFT),
                Arguments.of(initialState, MoveOrder.DOWN_RIGHT_LEFT_UP),
                Arguments.of(initialState, MoveOrder.LEFT_UP_DOWN_RIGHT),
                Arguments.of(initialState, MoveOrder.LEFT_UP_RIGHT_DOWN),
                Arguments.of(initialState, MoveOrder.UP_LEFT_DOWN_RIGHT),
                Arguments.of(initialState, MoveOrder.UP_LEFT_RIGHT_DOWN)
        );
    }

    static Stream<Arguments> generateDataForSolveForLength() {
        byte[] initialValues_6_steps = { 0, 2, 3, 4, 1, 5, 7, 8, 9, 6, 10, 12, 13, 14, 11, 15};
        byte[] initialValues_7_steps = { 2, 0, 3, 4, 1, 5, 7, 8, 9, 6, 11, 12, 13, 10, 14, 15};
        State initialState_6_steps = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues_6_steps, null, MoveDirection.NO_MOVE, 0);
        State initialState_7_steps = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues_7_steps, null, MoveDirection.NO_MOVE, 0);

        return Stream.of(
                Arguments.of(initialState_6_steps, MoveOrder.RIGHT_DOWN_UP_LEFT),
                Arguments.of(initialState_6_steps, MoveOrder.RIGHT_DOWN_LEFT_UP),
                Arguments.of(initialState_6_steps, MoveOrder.DOWN_RIGHT_UP_LEFT),
                Arguments.of(initialState_6_steps, MoveOrder.DOWN_RIGHT_LEFT_UP),
                Arguments.of(initialState_6_steps, MoveOrder.LEFT_UP_DOWN_RIGHT),
                Arguments.of(initialState_6_steps, MoveOrder.LEFT_UP_RIGHT_DOWN),
                Arguments.of(initialState_6_steps, MoveOrder.UP_LEFT_DOWN_RIGHT),
                Arguments.of(initialState_6_steps, MoveOrder.UP_LEFT_RIGHT_DOWN),

                Arguments.of(initialState_7_steps, MoveOrder.RIGHT_DOWN_UP_LEFT),
                Arguments.of(initialState_7_steps, MoveOrder.RIGHT_DOWN_LEFT_UP),
                Arguments.of(initialState_7_steps, MoveOrder.DOWN_RIGHT_UP_LEFT),
                Arguments.of(initialState_7_steps, MoveOrder.DOWN_RIGHT_LEFT_UP),
                Arguments.of(initialState_7_steps, MoveOrder.LEFT_UP_DOWN_RIGHT),
                Arguments.of(initialState_7_steps, MoveOrder.LEFT_UP_RIGHT_DOWN),
                Arguments.of(initialState_7_steps, MoveOrder.UP_LEFT_DOWN_RIGHT),
                Arguments.of(initialState_7_steps, MoveOrder.UP_LEFT_RIGHT_DOWN)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataForSolveForLength")
    void solve_for_length(State initialState, MoveOrder moveOrder) {
        BreadthFirstSearchSolver breadthFirstSearchSolver = new BreadthFirstSearchSolver(initialState, moveOrder);
        breadthFirstSearchSolver.solve();

        assertNotNull(breadthFirstSearchSolver);

        StringBuilder output = new StringBuilder();
        output.append("Solution info: \n");
        output.append("Solution length: " + breadthFirstSearchSolver.getSolutionInformation().getSolutionLength() + "\n");
        output.append("Solution moves: " + breadthFirstSearchSolver.getSolutionInformation().getSolutionMoves() + "\n");
        output.append("ExtraInformation info: \n");
        output.append("Solution length: " + breadthFirstSearchSolver.getExtraInformation().getSolutionLength() + "\n");
        output.append("Visited states: " + breadthFirstSearchSolver.getExtraInformation().getVisitedStates() + "\n");
        output.append("Processed states: " + breadthFirstSearchSolver.getExtraInformation().getProcessedStates() + "\n");
        output.append("Max recursion depth: " + breadthFirstSearchSolver.getExtraInformation().getMaxRecursionDepth() + "\n");
        output.append("Computation time: " + breadthFirstSearchSolver.getExtraInformation().getComputationTime() + "\n");
        System.out.println(output);
    }

    @ParameterizedTest
    @MethodSource("generateDataForExtraInformation")
    void getExtraInformation(State initialState, MoveOrder moveOrder) {
        BreadthFirstSearchSolver breadthFirstSearchSolver = new BreadthFirstSearchSolver(initialState, moveOrder);
        ExtraInformation extraInformation = breadthFirstSearchSolver.getExtraInformation();
        assertNotNull(extraInformation);
    }

    static Stream<Arguments> generateDataForExtraInformation() {
        byte[] initialValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        State initialState = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues, null, MoveDirection.NO_MOVE, 0);
        return Stream.of(
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_UP_LEFT)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataForSolutionInformation")
    void getSolutionInformation(State initialState, MoveOrder moveOrder) {
        BreadthFirstSearchSolver breadthFirstSearchSolver = new BreadthFirstSearchSolver(initialState, moveOrder);
        SolutionInformation solutionInformation = breadthFirstSearchSolver.getSolutionInformation();
        assertNotNull(solutionInformation);
    }

    static Stream<Arguments> generateDataForSolutionInformation() {
        byte[] initialValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        State initialState = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues, null, MoveDirection.NO_MOVE, 0);
        return Stream.of(
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_UP_LEFT)
        );
    }
}